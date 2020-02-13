// Server Program to Recieve a Text from Client through UDP 
// To Do Bigger Files
import java.io.*;
import java.net.*;
public class ServerUDPFile {
 
    public static void main(String[] args) {
        int port = 6000;
        try {
            System.out.println("Server Running..");
            DatagramSocket socket = new DatagramSocket(port);
            byte[] buffer = new byte[1024];
            DatagramPacket message = new DatagramPacket(buffer, buffer.length);
            socket.receive(message);

            String fileTypeLength = new String(buffer);
            String fileType = "";
            int fileLength = 0;
            int i = fileTypeLength.lastIndexOf('.');
            if (i > 0) {
                String str = fileTypeLength.substring(i+1);
                Long fileLen = Long.parseLong(str.trim());
                fileLength = Math.toIntExact(fileLen);
                fileType = fileTypeLength.substring(0, i);
            }
            File file = new File("./ServerFiles/recieved."+fileType);
            FileOutputStream fout = new FileOutputStream(file);
            
            byte[] byteArray = new byte[fileLength];
            DatagramPacket msg = new DatagramPacket(byteArray, byteArray.length);
            socket.receive(msg);
            fout.write(byteArray);

            System.out.println("File Written");
            fout.close();
            socket.close();
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}