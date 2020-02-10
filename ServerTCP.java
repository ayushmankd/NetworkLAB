// Server Program to Recieve Text From Client through TCP
import java.io.*;  
import java.net.*;  
public class ServerTCP {  
    public static void main(String[] args){  
        try{  
            System.out.println("Server Started..");
            ServerSocket ss=new ServerSocket(6666);  
            Socket s=ss.accept();  
            DataInputStream dis=new DataInputStream(s.getInputStream());  
            String  str=(String)dis.readUTF();  
            System.out.println("Message From Client: "+str);  
            ss.close();  
        }catch(Exception e){System.out.println(e);}  
    }  
}  