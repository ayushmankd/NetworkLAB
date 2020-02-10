// Client Program to send a Text to Server through UDP 
import java.io.*;
import java.net.*;
public class ClientUDP {
 
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        int port = 6000;
        try {
            System.out.println("Client is Sending Message..");
            DatagramSocket socket = new DatagramSocket();
            String msg = "Hello Server, I am Client";
            byte[] msg_bytes = msg.getBytes();
            DatagramPacket message = new DatagramPacket(msg_bytes, msg_bytes.length, address, port);
            socket.send(message);
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