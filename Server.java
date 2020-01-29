// Server Program
import java.io.*;  
import java.net.*;  
public class Server {
    private ServerSocket server_socket = null;
    private BufferedInputStream bin = null;
    private BufferedOutputStream bout = null;
    private DataInputStream din = null;
    private DataOutputStream dout = null;
    private Socket socket = null; 
    public Server(int port) {
        try {
            server_socket = new ServerSocket(port);
            System.out.println("Connected");
            socket = server_socket.accept();
            bin = new BufferedInputStream(socket.getInputStream());
            bout = new BufferedOutputStream(socket.getOutputStream());
            din = new DataInputStream(bin);
            dout = new DataOutputStream(bout);
        }
        catch(UnknownHostException u) { 
            System.out.println(u); 
        } 
        catch(IOException i) { 
            System.out.println(i); 
        }  
        try {
            dout.writeUTF("Server Says Hello");
            String inputClient = din.readUTF();
            System.out.println("Clinet Says: ");
            System.out.println(inputClient);
        } catch (IOException ie) {
            System.out.println(ie);
        }
        // close the connection 
        try
        { 
            din.close(); 
            dout.close(); 
            server_socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    }
    public static void main(String args[]) 
    { 
        Server server = new Server(3000); 
    } 
}
