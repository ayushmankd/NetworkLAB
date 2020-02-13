import java.io.File;
import java.util.Scanner;
// Testing Playgroud
class temp{
    public static void main(String[] args) throws Exception {
        try {
            File file = new File("./ClientFiles");
            String[] files = file.list();
            System.out.println("Files Available to Send are: ");
            for (int i = 0; i < files.length; i++) {
                System.out.println(i+" "+files[i]);
            }
            System.out.println("Enter the Index of File you want to send.");
            Scanner sc = new Scanner(System.in);
            int fileIndex = sc.nextInt();
            System.out.println("You Chose: "+files[fileIndex]);
            String extension = "";
            int i = files[fileIndex].lastIndexOf('.');
            if (i > 0) {
                extension = files[fileIndex].substring(i+1);
            }
            System.out.println(extension);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}