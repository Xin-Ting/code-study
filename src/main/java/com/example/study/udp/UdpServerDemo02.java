package com.example.study.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 还是要等待客户端的连接，并不是严格上的服务端，也可以是客户端
 */
public class UdpServerDemo02 {
    public static void main(String[] args) throws IOException {
        // 开放端口
        DatagramSocket socket = new DatagramSocket(9090);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
        // 接收数据包
        socket.receive(packet); //阻塞接收

        System.out.println(packet.getAddress().getHostAddress());
        System.out.println(new String(packet.getData(), 0, packet.getLength()));
        // 关闭连接
        socket.close();
    }
}
