// Client Program to send a Text to Server through TCP
import java.io.*;  
import java.net.*;  
public class ClientTCP {  
    public static void main(String[] args) {  
        try{      
            System.out.println("Client is Sending Message..");
            Socket s=new Socket("localhost",6666);  
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
            dout.writeUTF("Hello Server");  
            dout.flush();  
            dout.close();  
            s.close();  
        }catch(Exception e){System.out.println(e);}  
    }  
}  