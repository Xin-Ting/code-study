package com.example.study.base.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThreadDemo02 extends Thread {
    private String url;
    private String name;

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载类文件名为：" + name);
    }

    public TestThreadDemo02(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) {
        TestThreadDemo02 t1 = new TestThreadDemo02("https://p3fx.kgimg.com/v2/fxroomcover/2c4aad0136a6d4fa96781279771e28c4.jpg","1.jpg");
        TestThreadDemo02 t2 = new TestThreadDemo02("https://p3fx.kgimg.com/v2/fxroomcover/ea8e6362ac38edfc5a04c05a534064a3.jpg","2.jpg");
        TestThreadDemo02 t3 = new TestThreadDemo02("https://imgessl.kugou.com/stdmusic/20230920/20230920142503632013.jpg","3.jpg");
        t1.start();
        t2.start();
        t3.start();
    }


    class WebDownloader {
        public void downloader(String url, String name) {
            try {
                FileUtils.copyURLToFile(new URL(url), new File(name));
            } catch (IOException e) {
                System.out.println("IO异常：downloader");
            }
        }
    }
}
