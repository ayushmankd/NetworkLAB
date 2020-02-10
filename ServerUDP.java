// Server Program to Recieve a Text from Client through UDP 
import java.io.*;
import java.net.*;
public class ServerUDP {
 
    public static void main(String[] args) {
        int port = 6000;
        try {
            System.out.println("Server Running..");
            DatagramSocket socket = new DatagramSocket(port);
            byte[] buffer = new byte[512];
            DatagramPacket message = new DatagramPacket(buffer, buffer.length);
            socket.receive(message);
            String text = new String(buffer, 0, message.getLength());
            System.out.println(text);
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