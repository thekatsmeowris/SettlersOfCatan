// ObjectServer.java
package som;

import java.net.*;
import java.io.*;




public class ObjectServer extends Thread{
	private int port;
	private ServerSocket server;
	private boolean isOn;
	private ObjectInputStream in;
	private ObjectOutputStream out;


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

	public void start() {
		try {
			System.out.println("Server is on!!!");


			listen(server.accept());
			out.writeObject(player);
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		

	}

	public void listen(Socket socket){
		try{
			System.out.println(socket.getInetAddress().getHostName() + "("+ socket.getInetAddress().getHostAddress() + "): " + socket.getPort());
			// Read from socket to ObjectInputStream object
			in = new ObjectInputStream(socket.getInputStream());
			// Create ObjectOutputStream object
			out = new ObjectOutputStream(socket.getOutputStream());
	

			while (isOn){
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
			player++;
			System.out.println("#Player: " + player);

		}

		catch (Exception e){
			out.close();
			in.close();
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ObjectServer s = new ObjectServer(8888);
		s.start();
	}
}
