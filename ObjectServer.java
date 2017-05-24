// ObjectServer.java
import java.net.*;
import java.io.*;



public class ObjectServer {
	private int port;
	private ServerSocket server;


	// Create a server connection
	public ObjectServer(int p){
		try {
			port = p;
			server = new ServerSocket(port);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void start(){
		try {
			while (true) {
				// Get client connection 
				Socket socket = server.accept();
				System.out.println(socket.getLocalAddress().getHostName() + ": " + socket.getPort());
				// Read from socket to ObjectInputStream object
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				// Create ObjectOutputStream object
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				// Convert ObjectInputStream object to String
				String str = (String) in.readObject();
				System.out.println(str);
				out.writeObject(str);

				//Close all connection
				in.close();
	            out.close();
	            socket.close();

			}
			
			
		}

		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		

	}

	public static void main(String[] args) {
		ObjectServer s = new ObjectServer(8888);
		s.start();
	}
}