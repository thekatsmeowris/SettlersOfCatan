// ObjectServer.java
import java.net.*;
import java.io.*;



public class ObjectServer {
	private int port;
	private ServerSocket server;
	private Boolean isOn;


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
			while (isOn) {
				// Get client connection 
				Socket socket = server.accept();
				System.out.println(socket.getLocalAddress().getHostName() + ": " + socket.getPort());
				// Read from socket to ObjectInputStream object
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				// Create ObjectOutputStream object
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				listen();
				// out.writeObject(str);

				//Close all connection
				// in.close();
	   //          out.close();
	   //          socket.close();

			}
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		

	}

	public void listen(){
		while (isOn){
			Object inObject = in.readObject();

			// Convert ObjectInputStream object to String
			if (inObject instanceof String){
				String str = (String) inObject;
				System.out.println(str);
			}

			else;
		}
	}

	public static void main(String[] args) {
		ObjectServer s = new ObjectServer(8888);
		s.start();
	}
}