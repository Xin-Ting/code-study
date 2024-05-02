package com.example.study.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientDemo01 {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;
        try {
            //1.要知道服务器的地址和端口号
            InetAddress serverIP = InetAddress.getByName("127.0.0.1");
            int port = 9999;
            //2.建立socket连接
            socket = new Socket(serverIP, port);
            //3.发送消息，IO流
            os = socket.getOutputStream();
            os.write("hello world".getBytes());


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
