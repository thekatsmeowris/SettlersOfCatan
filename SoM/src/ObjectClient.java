// Client.java
import java.net.*;
import java.io.*;

public class ObjectClient{
	
	public static void main(String[] args) throws ClassNotFoundException, UnknownHostException, IOException{
		Socket s = new Socket(InetAddress.getLocalHost().getHostName(), 8888);
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		out.writeObject(""+123);
        String str = (String) in.readObject();
        System.out.println(str);
        out.close();
        in.close();
		s.close();
	}
}