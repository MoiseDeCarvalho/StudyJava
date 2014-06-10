package it.moise.study;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * La classe costruisce un server in ascolto sulla porta 30000 per un coda max di 10 client, 
 * finchè il client non invia il messaggio BYE resta in ascolto
 * @author De Carvalho Moise
 *
 */
public class DatagramEchoServer {
	private DatagramSocket socket;
	private DatagramPacket receiving_packet;
	private DatagramPacket sending_packet;
	
	public DatagramEchoServer() throws IOException{
		try{
			// creo il socket in ascolto sulla porta 30000
			socket = new DatagramSocket(30000);
			doProcessing();
		}
		finally { close(); }
	}

	private void doProcessing() throws IOException{
		String client_msg = "";
		do{
			showMessage("SEVER -> IN ATTESA DI PACCHETTI...");
			byte messages[] = new byte[200]; // impostazione buffer per la ricezione
			receiving_packet = new DatagramPacket(messages, messages.length);
			socket.receive(receiving_packet); // in attesa della ricezione di un pacchetto
			
			client_msg = new String(receiving_packet.getData(), 0, receiving_packet.getLength());
																
			showMessage("CLIENT HOST -> " + receiving_packet.getSocketAddress()
												+ " PORTA " 
												+ receiving_packet.getPort()
												+ " MESSAGGIO "
												+ client_msg);
			sendDataToClient(receiving_packet);
			
		}
		while (client_msg.trim().equals("BYE"));
	}
	
	private void showMessage(String msg) { System.out.println(msg); }
	
	private void sendDataToClient(DatagramPacket packet) throws IOException{
		// creo il pacchetto da inviare in echo
		sending_packet = new DatagramPacket(packet.getData(), packet.getLength(), packet.getAddress(), packet.getPort());
		socket.send(packet);
	}
	
	private void close() throws IOException{
		showMessage("SERVER -> CHIUSURA CONNESSIONE SOCKET");
		if(socket != null)
			socket.close();
	}
	
	public static void main(String args[]) throws IOException{
		new DatagramEchoServer();
	}
}
