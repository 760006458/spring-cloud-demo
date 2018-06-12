package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xuan
 * @create 2018-05-26 17:42
 **/
public class MyServer implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String line = reader.readLine();    //第三步
                System.out.println("服务端收到消息： " + line);
                String reverseStr = new StringBuilder(line).reverse().toString();
                writer.println(reverseStr); //第四步
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
