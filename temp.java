import java.io.File;
import java.io.FileInputStream;
// Testing Playgroud
class temp{
    public static void main(String[] args) throws Exception {
        try {
            File file = new File("./ExampleMessage.txt");
            FileInputStream fin = new FileInputStream(file);
            byte[] bytes = new byte[5];  
            // fin.read(bytes, 0, 5);
            // String message = new String(bytes);
            // System.out.println(message);
            fin.read(bytes, 2, 5);
            String message = new String(bytes);
            System.out.println(message);
            fin.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}