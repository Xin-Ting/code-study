package com.example.study.base.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * 可以返回值
 * 可以抛出异常
 */
public class TestThreadDemo05 implements Callable<Boolean> {
    private String url;
    private String name;

    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载类文件名为：" + name);
        return true;
    }

    public TestThreadDemo05(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThreadDemo05 t1 = new TestThreadDemo05("https://p3fx.kgimg.com/v2/fxroomcover/2c4aad0136a6d4fa96781279771e28c4.jpg", "1.jpg");
        TestThreadDemo05 t2 = new TestThreadDemo05("https://p3fx.kgimg.com/v2/fxroomcover/ea8e6362ac38edfc5a04c05a534064a3.jpg", "2.jpg");
        TestThreadDemo05 t3 = new TestThreadDemo05("https://imgessl.kugou.com/stdmusic/20230920/20230920142503632013.jpg", "3.jpg");

//        2. 创建执行任务：
        ExecutorService ser = Executors.newFixedThreadPool(3);

//        3. 提交执行：
        Future<Boolean> result1 = ser.submit(t1);
        Future<Boolean> result2 = ser.submit(t2);
        Future<Boolean> result3 = ser.submit(t3);

//        4. 获取结果：
        boolean r1 = result1.get();
        boolean r2 = result2.get();
        boolean r3 = result3.get();
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
//        5. 关闭服务：
        ser.shutdownNow();
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
