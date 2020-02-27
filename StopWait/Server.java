// Server Stop and Wait
// UDP
import java.io.*;
import java.net.*;
public class Server {
 
    public static void main(String[] args) {
        try {
            // Initialization
            System.out.println("Server Running..");
            DatagramSocket socket = new DatagramSocket(5678);
            DatagramSocket send_socket = new DatagramSocket();
            DatagramPacket send_packet;
            String nextFrameStr;
            byte[] nextFrameStrBytes;
            byte[] buffer = new byte[512];
            DatagramPacket message = new DatagramPacket(buffer, buffer.length);
            String text;
            int nextFrame = 1;
            InetAddress add;
            int rec_port = 0;
            do {
                // Receive Message until END is not received
                socket.receive(message);
                add = message.getAddress();
                rec_port = message.getPort();
                text = new String(buffer, 0, message.getLength());
                System.out.println(text);

                // Send Acknowledgement
                nextFrameStr = Integer.toString(nextFrame);
                nextFrameStrBytes = nextFrameStr.getBytes();
                send_packet = new DatagramPacket(
                    nextFrameStrBytes,
                    nextFrameStrBytes.length,
                    add,
                    3000
                );
                send_socket.send(send_packet);
                nextFrame += 1;
            } while (text.compareTo("END") != 0);


            System.out.println("All Frames from Client were Received!!");
            socket.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}