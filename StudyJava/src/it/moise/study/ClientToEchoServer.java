package it.moise.study;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * La classe costruisce un client che comunica con il server, il server stampa a video i messaggi del client e reasta in ascolto fino
 * a quando il cliente non il invia il messaggio di BYE
 * @author De Carvalho Moise
 *
 */
public class ClientToEchoServer {
	private  Socket socket;
	private ObjectOutputStream output_stream;
	private ObjectInputStream input_stream;
	
	public ClientToEchoServer() throws IOException, ClassNotFoundException {
		try{
			attemptToConnect();
			createStreams();
			initProcessing();
		}
		finally { close(); }
	}

	private void attemptToConnect() throws IOException{
		showMessage("CLIEN -> CONNESSIONE VERSO UN SERVER...");
		socket = new Socket("localhost", 30000); // connessione al server sulla porta 30000
		showMessage("CLIENT -> CONNESSIONE AVVENUTA VERSO " 	  
															+ socket.getInetAddress() 
															+ " ALLA PORTA REMOTA " 
															+ socket.getPort() 
															+ " E ALLA PORTA LOCALE " 
															+ socket.getLocalPort());
	}
	
	private void createStreams() throws IOException{
		output_stream = new ObjectOutputStream(socket.getOutputStream());
		output_stream.flush();
		input_stream = new ObjectInputStream(socket.getInputStream());
		showMessage("CLIENT -> STREAMS CREATI");
	}
	
	private void initProcessing() throws IOException, ClassNotFoundException {
		// messaggio di benvenuto del server
		String server_msg = (String) input_stream.readObject();
		
		showMessage(server_msg);
		do{
			sendDataToServer(readFromInput());
			server_msg = (String) input_stream.readObject();
			showMessage(server_msg);
		}
		while (!server_msg.contains("BYE"));
	}
	
	private void showMessage(String msg) { System.out.println(msg); }
	
	private void sendDataToServer(String msg) throws IOException{
		output_stream.writeObject(msg);;
		output_stream.flush();
	}
	
	private void close() throws IOException{
		showMessage("CLIENT -> CHIUSURA CONNESSIONE SOCKET");
		
		if (output_stream != null && input_stream != null && socket != null){
			output_stream.close();
			input_stream.close();
			socket.close();
		}
	}
	
	private String readFromInput(){
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		new ClientToEchoServer();

	}

}
