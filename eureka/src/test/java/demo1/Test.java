package demo1;

/**
 * @author xuan
 * @create 2018-05-26 17:49
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new MyServer()).start();
        Thread.sleep(1000);
        new Thread(new MyClient()).start();
    }
}
