// Client Program to send Text File to Server through TCP
// Works for Small files 
import java.io.*;  
import java.net.*;  
public class ClientTCPTextFile {  
    public static void main(String[] args) throws Exception{  
        try{      
            System.out.println("Client is sending Text File..");
            Socket s=new Socket("localhost",6666);  
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());  
            DataInputStream din = new DataInputStream(s.getInputStream());
            File file = new File("./ExampleMessage.txt");
            FileInputStream fin = new FileInputStream(file);
            long fileLength = file.length();
            int fileLengthInt = Math.toIntExact(fileLength);
            byte[] byteArray = new byte[fileLengthInt];
            dout.writeUTF(""+fileLength);  
            dout.flush();
            String response = (String)din.readUTF();
            if (response.compareTo("OK") == 0){
                fin.read(byteArray);
                // byteArray, offset, length
                // fin.read(byteArray, 0, 10);
                // String msg = new String(byteArray);
                // System.out.println(msg);
                dout.write(byteArray);
            }
            fin.close();
            dout.flush();  
            dout.close();  
            s.close();  
        }catch(Exception e){System.out.println(e);}  
    }  
}  