// Client.java
package som;

import java.net.*;
import java.io.*;

public class ObjectClient {

    private Socket s;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ObjectClient(String ip, int port) {
        try {
            s = new Socket(ip, port);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());

            // String str = (String) in.readObject();
            // System.out.println(str);
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Object o) throws IOException {
        out.writeObject(o);
    }

    public Object read() throws IOException, ClassNotFoundException {

        return (Object) in.readObject();

    }

    public Boolean hasConnection() {
        return s != null;
    }

    public void close() {
        try {
            out.close();
            in.close();
            s.close();
            s = null;
        } catch (IOException e) {
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
