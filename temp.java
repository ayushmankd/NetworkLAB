import java.io.*;

class thr extends Thread{
    int num1;
    thr(int num) {
        num1 = num;
    }
    public void run() {
        System.out.println("Counter: "+num1);
    }
}
class temp{
    public static void main(String[] args) {
        int num = 1;
        thr t = new thr(num);
        num += 1;
        thr t1 = new thr(num);
        t.start();
        t1.start();
    }
}