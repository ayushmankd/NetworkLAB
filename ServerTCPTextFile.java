// Server Program to Recieve Text File From Client through TCP
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
            int bufferSize = 5;
            byte[] byteArr = new byte[fileLengthComingInt];
            int currentOffset = 0;
            byte[] bytes = new byte[bufferSize];
            while ((currentOffset+bufferSize) < fileLengthComingInt) {
                din.read(bytes);
                for(int i=0;i<bufferSize;i++){
                    byteArr[currentOffset] = bytes[i];
                    currentOffset+=1;
                }
                dout.writeUTF("OK");
            }
            din.read(bytes);
            for(int i=0; i<fileLengthComingInt-currentOffset; i++){
                byteArr[currentOffset+i] = bytes[i];
            }
            fout.write(byteArr);
            dout.writeUTF("Done");
            fout.close();
            ss.close();  
        }catch(Exception e){System.out.println(e);}  
    }  
}  