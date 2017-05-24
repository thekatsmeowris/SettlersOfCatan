// Client.java
package som;
import java.net.*;
import java.io.*;

public class ObjectClient{
	private Socket s;
	ObjectOutputStream out;
	ObjectInputStream in;

	public ObjectClient(String ip, int port){
		try{
			Socket s = new Socket(ip,port);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			out.writeObject(""+123);
			// String str = (String) in.readObject();
			// System.out.println(str);
	    }
		catch (IOException e){
	    	e.printStackTrace();
	    }

	}

	public Boolean isConnected(){
		return s.isConnected();
	}

	public void close(){
		try{
			out.close();
			in.close();
			s.close();
		}
	    catch (IOException e){
	    	e.printStackTrace();
	    }

	}

	// public static void main(String[] args) {
	// 	try{
	// 		new ObjectClient(InetAddress.getLocalHost().getHostName(), 8888);
	// 	}
	// 	catch (UnknownHostException unknownhost){
	//     	unknownhost.printStackTrace();
	//     }
	// }
}