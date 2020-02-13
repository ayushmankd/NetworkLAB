// Client Program for Multithreaded Message Transfer
import java.net.*;
import java.util.*;
import java.io.*;
public class ClientMulti{
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost", 5678);
            System.out.println("Client Started");
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            String messageToSend = "Hello, I am a Client.";
            dout.writeUTF(messageToSend);
            String messageFromServer = (String)din.readUTF();
            System.out.println("Message From Server: ");
            System.out.println(messageFromServer);
            Scanner ss = new Scanner(System.in);
            String msg = ss.nextLine();
            do {
                dout.writeUTF(msg);
                messageFromServer = (String)din.readUTF();
                System.out.println(messageFromServer);
                msg = ss.nextLine();
            } while (msg.compareTo("END") != 0);
            dout.writeUTF("STOP");
            s.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}