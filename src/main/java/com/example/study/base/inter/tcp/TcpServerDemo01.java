package com.example.study.base.inter.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo01 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            //1.需要一个服务端地址
            serverSocket = new ServerSocket(9999);
            while (true) {
                // 2.等待客户端建立连接
                socket = serverSocket.accept();
                // 3.读取客户端消息 管道流
                is = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int len;
                bos = new ByteArrayOutputStream();
                while ((len = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                System.out.println(bos.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
