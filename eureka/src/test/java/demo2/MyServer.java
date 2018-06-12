package demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author xuan
 * @create 2018-05-26 17:42
 **/
public class MyServer implements Runnable {

    private final Socket socket;

    public MyServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String line = reader.readLine();    //第三步
                System.out.println("服务端收到" + socket.getPort() + "的消息： " + line);
                String reverseStr = new StringBuilder(line).reverse().toString();
                writer.println(reverseStr); //第四步
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
