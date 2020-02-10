// Server Program to Recieve Text File From Client through TCP
// works for small files
import java.io.*;  
import java.net.*;  
public class ServerTCPTextFile {  
    public static void main(String[] args){  
        try{  
            System.out.println("Server Started..");
            ServerSocket ss=new ServerSocket(6666);  
            Socket s=ss.accept();  
            DataInputStream din=new DataInputStream(s.getInputStream());  
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            long fileLengthComing = Long.parseLong(din.readUTF());
            dout.writeUTF("OK");
            File file = new File("./example_server.txt");
            FileOutputStream fout = new FileOutputStream(file);
            int fileLengthComingInt = Math.toIntExact(fileLengthComing);
            byte[] byteArr = new byte[fileLengthComingInt];
            din.read(byteArr);
            fout.write(byteArr);
            fout.close();
            ss.close();  
        }catch(Exception e){System.out.println(e);}  
    }  
}  