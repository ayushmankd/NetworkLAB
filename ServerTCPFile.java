// Server Program to Recieve Any File From Client through TCP
// To Do Progress Bar
import java.io.*;  
import java.net.*;  
public class ServerTCPFile {  
    public static void main(String[] args){  
        try{  
            System.out.println("Server Started..");
            ServerSocket ss=new ServerSocket(6666);  
            Socket s=ss.accept();  
            DataInputStream din=new DataInputStream(s.getInputStream());  
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            int fileOption = Integer.parseInt(din.readUTF());
            long fileLengthComing = Long.parseLong(din.readUTF());
            dout.writeUTF("OK");
            File file = null;
            switch (fileOption) {
                case 1:
                    file = new File("./ServerFiles/ServerText.txt");
                    break;
                case 2:
                    file = new File("./ServerFiles/ServerImage.jpg");
                    break;
                case 3:
                    file = new File("./ServerFiles/ServerAudio.ogg");
                    break;
                case 4:
                    file = new File("./ServerFiles/ServerVideo.mp4");
                    break;
                
                default:
                    break;
            }
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
            System.out.println("DONE");
            dout.writeUTF("Done");
            fout.close();
            ss.close();  
        }catch(Exception e){System.out.println(e);}  
    }  
}  