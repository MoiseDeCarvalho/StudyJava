package it.moise.study;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * La classe costruisce un client che comunica con il server, il server stampa a video i messaggi del client e reasta in ascolto fino
 * a quando il cliente non il invia il messaggio di BYE
 * @author De Carvalho Moise
 *
 */
public class DatagramClientEchoServer {
	private DatagramSocket socket;
	private DatagramPacket receiving_packet;
	private DatagramPacket sending_packet;
	
	public DatagramClientEchoServer() throws IOException{
		try{
			// creo il socket
			socket = new DatagramSocket();
			doProcessing();
		}
		finally { close(); }
	}

	private void doProcessing() throws IOException{
		String server_msg = "";
		do{
			showMessage("CLIENT -> DIGITA DEL TESTO DA INVIARE AL SERVER");
			sendDataToServer(readFromInput());
			
			byte messages[] = new byte[200]; // impostazione del buffer per la ricezione
			receiving_packet = new DatagramPacket(messages, messages.length);
			socket.receive(receiving_packet); // in attesa della ricezione di un pacchetto
			
			server_msg = new String(receiving_packet.getData(), 0 , receiving_packet.getLength());
			
			showMessage("SERVER HOST -> " + receiving_packet.getAddress()
											+ " PORTA " 
											+ receiving_packet.getPort()
											+ " MESSAGGIO "
											+ server_msg);
			
		}
		while (!server_msg.trim().equals("BYE"));
	}
	
	private void showMessage(String msg) { System.out.println(msg); }
	
	private void sendDataToServer(DatagramPacket packet) throws IOException {
		socket.send(packet);
	}
	
	private DatagramPacket readFromInput() throws UnknownHostException{
		Scanner in = new Scanner(System.in); // preparo il testo da inviare
		String message = in.nextLine();
		byte data[] = message.getBytes();
		sending_packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 30000);
		return sending_packet;
	}
	private void close() throws IOException{
		showMessage("SERVER -> CHIUSURA CONNESSIONE SOCKET");
		if (socket != null)
			socket.close();
	}
	
	public static void main(String args[]) throws IOException{
		new DatagramClientEchoServer();
	}
}
