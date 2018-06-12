package demo2;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端每次跟服务端建立连接，服务端都开辟一条线程专门处理该客户端的socket
 *
 * @author xuan
 * @create 2018-05-26 17:49
 **/
public class Test {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new MyServer(socket)).start();
        }
    }
}
