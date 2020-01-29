// Client Program 
import java.net.*; 
import java.io.*; 
  
public class Client 
{ 
    // initialize socket and input output streams 
    private Socket socket = null; 
    private BufferedInputStream bin = null;
    private BufferedOutputStream bout = null;
    // private DataInputStream din = null;
    private BufferedReader din = null;
    private DataOutputStream dout = null;

    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
            System.out.println("Write Something to Send to Server");
            System.out.println("End it by writing END");
  
            // takes input from terminal 
            // bin = new BufferedInputStream(System.in); 
            // sends output to the socket 
            bout = new BufferedOutputStream(socket.getOutputStream()); 
            // din = new DataInputStream(System.in);
            din = new BufferedReader(new InputStreamReader(System.in));
            dout = new DataOutputStream(bout);
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = "---FROM CLIENT---\n"; 
  
        // keep reading until "Over" is input 
        // while (!line.equals("END")) 
        // { 
            try
            { 
                line = din.readLine(); 
                dout.writeUTF(line); 
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        // } 
  
        // close the connection 
        try
        { 
            din.close(); 
            dout.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 3000); 
    } 
} 