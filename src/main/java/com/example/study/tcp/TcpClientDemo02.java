package com.example.study.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientDemo02 {
    public static void main(String[] args) throws IOException {
        // 1.创建socket连接
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9000);
        // 2.创建输出流
        OutputStream os = socket.getOutputStream();
        // 3.读取文件
        FileInputStream fis = new FileInputStream("HELP.md");
        // 4.写入文件
        byte[] buffer1 = new byte[1024];
        int len;
        if ((len = fis.read(buffer1)) != -1) {
            os.write(buffer1,0,len);
        }
        // 通知服务器已经传输完
        socket.shutdownOutput();

        // 5.确定服务端接收完毕，才断开连接
        InputStream inputStream = socket.getInputStream();
        byte[] buffer2 = new byte[1024];
        int len2;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 将读取的内容写入缓冲区
        while ((len2 = inputStream.read(buffer2)) != -1) {
            // 将缓冲区的内容输出
            baos.write(buffer2,0,len2);
        }
        System.out.println(baos);

        baos.close();
        inputStream.close();
        fis.close();
        os.close();
        socket.close();
    }
}
