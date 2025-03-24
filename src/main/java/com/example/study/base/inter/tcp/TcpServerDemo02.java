package com.example.study.base.inter.tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo02 {
    public static void main(String[] args) throws IOException {
        // 1.创建服务器地址
        ServerSocket serverSocket = new ServerSocket(9000);
        // 2.等待客户端连接
        Socket socket = serverSocket.accept(); //阻塞式监听，会一直等待客户端连接
        // 3.获取输入流
        InputStream is = socket.getInputStream();

        // 4.输出文件
        FileOutputStream fos = new FileOutputStream("ceShiTcp.md");
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer,0,len);
        }

        // 5.通知客户端接收完毕
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("我接收完毕了".getBytes());

        outputStream.close();
        fos.close();
        is.close();
        socket.close();
        serverSocket.close();
    }
}
