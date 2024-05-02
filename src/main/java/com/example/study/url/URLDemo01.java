package com.example.study.url;

import java.io.IOException;
import java.net.URL;

public class URLDemo01 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:8080/helloworld/index.jsp?username=xxxx");
        System.out.println("Protocol=" + url.getProtocol());
        System.out.println("Host=" + url.getHost());
        System.out.println("Port=" + url.getPort());
        System.out.println("Path=" + url.getPath());
        System.out.println("File=" + url.getFile());
        System.out.println("Query=" + url.getQuery());
        System.out.println("Authority=" + url.getAuthority());
        System.out.println("Content=" + url.getContent());
    }
}
