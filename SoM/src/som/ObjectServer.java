// ObjectServer.java
package som;

import java.net.*;
import java.io.*;




public class ObjectServer{
	private int port;
	private ServerSocket server;
	private boolean isOn;
	

	//Logic
	private int player;
	

	// Create a server connection
	public ObjectServer(int p){
		try {
			port = p;
			server = new ServerSocket(port);
			isOn = true;

		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void runServer(){
		try {
			System.out.println("Server is on!!!");

			while (isOn){
				new Thread(new ClientThread(server.accept(), player++)).start();	
				
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ObjectServer(8888).runServer();
	}
}

class ClientThread implements Runnable{
	private Socket socket;
	private int player;

	public ClientThread(Socket s, int p){
		socket = s;
		player = p;
		System.out.println("#Player: " + player);	
	}

	public void run(){
		ObjectInputStream in = null;
		ObjectOutputStream out = null;

		try{
			System.out.println(socket.getInetAddress().getHostName() + "("+ socket.getInetAddress().getHostAddress() + "): " + socket.getPort());
			// Read from socket to ObjectInputStream object
			in = new ObjectInputStream(socket.getInputStream());


			// Create ObjectOutputStream object
			out = new ObjectOutputStream(socket.getOutputStream());
	

			while (true){
				Object inObject = in.readObject();

				// Convert ObjectInputStream object to String
				if (inObject instanceof String){
					String str = (String) inObject;
					System.out.println(str);
				}

				else{
					System.out.println(inObject.getClass().getName());
				}
					
				
			}
			

		}

		catch (Exception e){
			e.printStackTrace();
		}

		finally{
			try{
				in.close();
				out.close();
				socket.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}

	}
}
