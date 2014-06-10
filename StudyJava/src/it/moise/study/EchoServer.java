package it.moise.study;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * La classe costruisce un server in ascolto sulla porta 30000 per un coda max di 10 client, 
 * finchè il client non invia il messaggio BYE resta in ascolto
 * @author De Carvalho Moise
 *
 */
public class EchoServer {
	private ServerSocket server_socket;
	private Socket socket;
	private ObjectOutputStream output_stream;
	private ObjectInputStream input_stream;
	
	public EchoServer() throws IOException, ClassNotFoundException{
		try{ // creo il socket in ascolto sulla porta 30000
			// per un amx di 10 client
			
			server_socket = new ServerSocket(30000, 10);
			listen();
			createStreams();
			initProcessing();
		}
		finally { close(); }
	}
	
	private void listen() throws IOException{
		showMessage("SERVER -> IN ATTESA DI CONNESSIONI...");
		socket = server_socket.accept(); // pongo il server in attesa di connessioni
		showMessage("SERVER -> CONNESSO CON " + socket.getInetAddress() 
											+ " ALLA PORTA REMOTA "
											+ socket.getPort() 
											+ " E ALLA PORTA LOCALE " 
											+ socket.getLocalPort());
		
	}
	
	private void createStreams() throws IOException{
		output_stream = new ObjectOutputStream(socket.getOutputStream());
		output_stream.flush();
		
		input_stream = new ObjectInputStream(socket.getInputStream());
		showMessage("SERVER -> STREAMS CREATI");
	}
	
	private void initProcessing() throws IOException, ClassNotFoundException{
		String client_msg = "";
		sendDataToClient("SERVER -> Ciao digita BYE per terminare");
		do{
			client_msg = (String) input_stream.readObject();
			showMessage("CLIENT -> " + client_msg);
			sendDataToClient("SERVER ECHO: -> " + client_msg);
		}
		while (!client_msg.trim().equals("BYE"));
	}

	private void showMessage(String msg) { System.out.println(msg); }
	private void sendDataToClient(String msg) throws IOException{
		output_stream.writeObject(msg);
		output_stream.flush();
	}
	
	private void close() throws IOException{
		showMessage("SERVER -> CHIUSEURA CONNESSIONE SOCKET");
		
		if (output_stream != null && input_stream != null && socket != null){
			output_stream.close();
			input_stream.close();
			socket.close();
		}
	}
	public static void main(String[] args) throws IOException , ClassNotFoundException {
		new EchoServer();

	}

}
