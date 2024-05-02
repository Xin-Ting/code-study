package com.example.study.udp.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkReceive implements Runnable {
    DatagramSocket socket = null;
    private int fromPort;

    private String fromPerson;

    public TalkReceive(int fromPort, String fromPerson) {
        this.fromPort = fromPort;
        this.fromPerson = fromPerson;
        try {
            socket = new DatagramSocket(fromPort);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 准备接收包
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
                socket.receive(packet); // 阻塞式接收
                byte[] data = packet.getData();
                String receiveData = new String(data, 0, packet.getLength());
                System.out.println(this.fromPerson + ":" + receiveData);
                // 断开连接
                if ("bye".equals(receiveData)) {
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        socket.close();
    }
}
