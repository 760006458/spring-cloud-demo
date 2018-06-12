package demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author xuan
 * @create 2018-05-26 17:42
 **/
public class MyClient implements Runnable {

    @Override
    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();   //第一步
                writer.println(line);   //第二步
                System.out.println("客户端收到服务端返回的消息：" + reader.readLine());   //第五步
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
