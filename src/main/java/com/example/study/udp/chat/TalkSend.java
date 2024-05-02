package com.example.study.udp.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkSend implements Runnable {
    DatagramSocket socket = null;
    BufferedReader reader = null;

    private int fromPort;
    private String toIP;
    private int toPort;

    public TalkSend(int fromPort, String toIP, int toPort) {
        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toPort = toPort;

        try {
            // 1.建立一个socket
            socket = new DatagramSocket(fromPort);
            // 2.准备数据，控制台读取system.in
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                String data = reader.readLine();
                byte[] bytes = data.getBytes();
                // 数据，数据的长度起始，要发送的地址
                DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress(this.toIP, this.toPort));

                // 3.发送包
                socket.send(packet);
                if ("bye".equals(data)) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // 4.关闭流
        socket.close();
    }
}
