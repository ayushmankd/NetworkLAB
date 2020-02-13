// Server Program for Multithreaded Message Transfer
// To Do Closing of Server Connection
import java.net.*;
import java.util.*;
import java.io.*;
class ThreadConnection extends Thread{
    int clientNumber;
    Socket socket;
    DataInputStream din;
    DataOutputStream dout;
    ThreadConnection(Socket s, int num) {
        clientNumber = num;
        socket = s; 
    }
    public void run() {
        try{
            System.out.println("Running Thread");
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            String msgFromClient = (String)din.readUTF();
            System.out.println(msgFromClient);
            String msgToClient = "Hello Client";
            dout.writeUTF(msgToClient+" "+clientNumber+"Tell Something");
            msgFromClient = (String)din.readUTF();
            while(msgFromClient.compareTo("STOP") != 0) {
                msgToClient = "Okay Client";
                dout.writeUTF(msgToClient+" "+clientNumber+" "+msgFromClient);
                msgFromClient = (String)din.readUTF();
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class ServerMulti{
    public static void main(String[] args) {
        int clientNum = 1;
        try{
            System.out.println("Server Started..");
            ServerSocket ss=new ServerSocket(5678);  
            System.out.println("Enter END to terminate Server"); 
            Scanner sc = new Scanner(System.in); 
            String command = null;
            do {
                Socket s=ss.accept();
                ThreadConnection t = new ThreadConnection(s, clientNum);
                clientNum += 1;
                t.start();
                // command = sc.nextLine();
            // } while (command.compareTo("END") != 0);
            } while (true);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // ss.close();  
        }
    }
}