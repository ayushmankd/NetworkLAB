// Client Program to send Text File to Server through TCP
import java.io.*;  
import java.net.*; 
import java.util.*; 
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

            int bufferSize = 5;
            byte[] byteArray = new byte[fileLengthInt];
            fin.read(byteArray);
            dout.writeUTF(""+fileLength);  
            dout.flush();
            String response = (String)din.readUTF();
            int currOffset = 0;
            byte[] bytes = new byte[bufferSize];
            while (response.compareTo("OK") == 0) {
                if ((currOffset+bufferSize) <= fileLengthInt) {
                    bytes = Arrays.copyOfRange(byteArray, currOffset, currOffset+bufferSize);
                    dout.write(bytes);
                } else {
                    bytes = Arrays.copyOfRange(byteArray, currOffset, fileLengthInt);
                    dout.write(bytes);
                }
                dout.flush();
                currOffset += bufferSize;
                response = (String)din.readUTF();
            }
            fin.close();
            dout.flush();  
            dout.close();  
            s.close();  
        }catch(Exception e){System.out.println(e);}  
    }  
}  