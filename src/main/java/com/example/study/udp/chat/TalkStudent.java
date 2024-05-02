package com.example.study.udp.chat;

public class TalkStudent {
    public static void main(String[] args) {
        new Thread(new TalkSend(6666, "127.0.0.1", 8888)).start();
        new Thread(new TalkReceive(9999, "老师")).start();
    }
}
