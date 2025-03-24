package com.example.study.base.inter.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UdpSendDemo01 {
    public static void main(String[] args) throws IOException {
        // 1.建立一个socket
        DatagramSocket socket = new DatagramSocket(8888);

        // 2.准备数据，控制台读取system.in
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String data = reader.readLine();
            byte[] bytes = data.getBytes();
            // 数据，数据的长度起始，要发送的地址
            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("127.0.0.1", 6666));

            // 3.发送包
            socket.send(packet);
            if ("bye".equals(data)) {
                break;
            }
        }

        // 4.关闭流
        socket.close();
    }
}
