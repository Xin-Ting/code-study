package com.example.study.udp.chat;

public class TalkTeacher {
    public static void main(String[] args) {
        new Thread(new TalkSend(5555, "127.0.0.1", 9999)).start();
        new Thread(new TalkReceive(8888, "学生")).start();
    }
}
