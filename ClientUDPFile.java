// Client Program to send a Text to Server through UDP 
// To DO for Bigger Files
import java.io.*;
import java.net.*;
import java.util.*;
public class ClientUDPFile {
 
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        int port = 6000;
        try {
            // Initialize Socket
            System.out.println("Client is Sending Message..");
            DatagramSocket socket = new DatagramSocket();

            // Display Files that can be sent
            File file = new File("./ClientFiles");
            String[] files = file.list();
            System.out.println("Files Available to Send are: ");
            for (int i = 0; i < files.length; i++) {
                System.out.println(i+" "+files[i]);
            }
            // Select the File to Send
            System.out.println("Enter the Index of File you want to send.");
            Scanner sc = new Scanner(System.in);
            int fileIndex = sc.nextInt();
            System.out.println("You Chose: "+files[fileIndex]);

            // Open the File in InputStream to send
            File fileToSend = new File("./ClientFiles/"+files[fileIndex]);
            FileInputStream fin = new FileInputStream(fileToSend);

            // Get the Extension of the File
            String extension = "";
            int i = files[fileIndex].lastIndexOf('.');
            if (i > 0) {
                extension = files[fileIndex].substring(i+1);
            }

            // Send the File Extension and File Length
            String fileInfo = extension+"."+fileToSend.length();
            byte[] fileInfoByte = fileInfo.getBytes();
            DatagramPacket msg = new DatagramPacket(fileInfoByte, fileInfoByte.length, address, port);
            socket.send(msg);

            // Convert the File into Byte Array
            byte[] file_bytes = new byte[(int)fileToSend.length()];
            fin.read(file_bytes);

            // Send the File
            DatagramPacket message = new DatagramPacket(file_bytes, file_bytes.length, address, port);
            socket.send(message);
            fin.close();
            sc.close();
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