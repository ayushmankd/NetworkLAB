// Client Program Stop and Wait Protocol UDP
import java.io.*;
import java.net.*;
import java.util.*;
public class Client {
 
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        try {
            // Initialization
            int num_frames = 0;
            DatagramSocket send_socket = new DatagramSocket();
            DatagramSocket rec_socket = new DatagramSocket(3000);
            Scanner s = new Scanner(System.in);
            int seq_num = 0;
            String msg = null;
            String msgRec = null;
            byte[] msg_bytes = new byte[128];
            byte[] msg_rec = new byte[256];
            DatagramPacket messageToSend;
            DatagramPacket msgRecPacket;
            int nextFrame;

            // Getting Number of Frames to Send
            System.out.println("Enter the Number of Frames to be sent.");
            num_frames = s.nextInt();
            System.out.println("Client will send "+num_frames+" Frames.");

            while (true) {
                // Sending Frames
                msg = "Frame Number: "+seq_num;
                msg_bytes = msg.getBytes();
                messageToSend = new DatagramPacket(
                    msg_bytes, 
                    msg_bytes.length, 
                    address, 
                    5678
                );
                send_socket.send(messageToSend);

                // Waiting to Recieve Acknowledgement
                Thread.sleep(1000);

                // Getting Acknowledgement
                msgRecPacket = new DatagramPacket(msg_rec, msg_rec.length);
                rec_socket.receive(msgRecPacket);
                msgRec = new String(msg_rec);
                System.out.println("Message From Server (Frame Request)");
                System.out.println("Next Frame: "+msgRec);
                nextFrame = Integer.parseInt(msgRec.trim());

                // Checking if All Frames have been Sent
                // If YES then send END Message
                if (nextFrame == num_frames) {
                    msg = "END";
                    msg_bytes = msg.getBytes();
                    messageToSend = new DatagramPacket(
                        msg_bytes, 
                        msg_bytes.length, 
                        address, 
                        5678
                    );
                    send_socket.send(messageToSend);
                    break;
                }
                // Prepare Next Frame
                else if (nextFrame == (seq_num+1)) {
                    seq_num += 1;
                } else {
                    // Resend the Frame
                    // Because it didn't get request for the frame expected
                    System.out.println("Didn't get Any Request for Next Frame, resending this");
                    continue;
                }
            }
            System.out.println("All Frames Successfully Sent!!");
            send_socket.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}