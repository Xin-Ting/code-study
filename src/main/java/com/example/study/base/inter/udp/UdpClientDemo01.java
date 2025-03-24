package com.example.study.base.inter.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 不需要连接服务器
 */
public class UdpClientDemo01 {
    public static void main(String[] args) throws IOException {
        // 1.建立一个socket
        DatagramSocket socket = new DatagramSocket();
        // 2.发送给谁
        String msg = "你好，世界！";
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        int port = 9090;
        // 数据，数据的长度起始，要发送的地址
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, ip, port);

        // 3.发送包
        socket.send(packet);

        // 4.关闭流
        socket.close();
    }
}
