import java.io.File;
import java.io.FileInputStream;

class temp{
    public static void main(String[] args) throws Exception {
        try {
            File file = new File("./example_server.txt");
            FileInputStream fin = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];  
            fin.read(bytes);
            String message = new String(bytes);
            System.out.println(message);
            fin.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}