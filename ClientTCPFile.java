// Client Program to send Any File to Server through TCP
// Progress Bar
// Client Choose his own file
import java.io.*;  
import java.net.*; 
import java.util.*; 
public class ClientTCPFile {  
    public static void main(String[] args) throws Exception{  
        try{      
            // Initialization
            System.out.println("Client is sending Text File..");
            Socket s=new Socket("localhost",6666);  
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());  
            DataInputStream din = new DataInputStream(s.getInputStream());

            // Tell the User to Select which type of file it wants to send
            // The Files are predefined
            System.out.println("Select the Options From Below (Only Enter the Number): ");
            System.out.println("1. Send a Text File");
            System.out.println("2. Send a Image File");
            System.out.println("3. Send a Audio File");
            System.out.println("4. Send a Video File");
            Scanner scan = new Scanner(System.in);
            int option = scan.nextInt();
            scan.close();

            // Tell the Server which option the User Selected
            dout.writeUTF(""+option);

            // Open the File for Reading
            File file = null;
            switch (option) {
                case 1:
                    file = new File("./ClientFiles/ExampleMessage.txt");
                    break;
                case 2:
                    file = new File("./ClientFiles/Image.jpg");
                    break;
                case 3:
                    file = new File("./ClientFiles/sample_audio.ogg");
                    break;
                case 4:
                    file = new File("./ClientFiles/sample_video.mp4");
                    break;
                default:
                    break;
            }

            // File Operations
            FileInputStream fin = new FileInputStream(file);
            long fileLength = file.length();
            int fileLengthInt = Math.toIntExact(fileLength);
            
            // Convert File into Byte Array
            byte[] byteArray = new byte[fileLengthInt];
            fin.read(byteArray);

            // Send the File Length to Server
            dout.writeUTF(""+fileLength);  
            dout.flush();


            // Send buffers of file
            int bufferSize = 5;
            String response = (String)din.readUTF();
            int currOffset = 0;
            byte[] bytes = new byte[bufferSize];
            // Continue Sending Buffers until the Server Says So
            // The Server checks the number of chunks and it's size and all
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