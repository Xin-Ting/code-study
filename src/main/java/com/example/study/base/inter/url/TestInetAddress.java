package com.example.study.base.inter.url;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 测试ip
 */
public class TestInetAddress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        InetAddress[] baidu = InetAddress.getAllByName("www.baidu.com");
        System.out.println(baidu);

        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(byName);

        System.out.println(byName.getAddress());
        System.out.println(byName.getHostName()); // 域名或者自己电脑的名字
        System.out.println(byName.getHostAddress()); // ip
        System.out.println(byName.getCanonicalHostName()); // 规范的名字
    }
}
