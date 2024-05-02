package com.example.study.url;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLDemo02 {
    public static void main(String[] args) throws IOException {

        // 1.下载地址
        URL url = new URL("https://m10.music.126.net/20240503002821/55400c5bddc4703c8fecfcad00cb0fea/yyaac/obj/wonDkMOGw6XDiTHCmMOi/14055854047/d31d/0a87/5a01/1b9cb2b909682f09703924b1481a5ba2.m4a");
//        URL url = new URL("https://webfs.hw.kugou.com/202405022233/dc1dadd61ffc3624cdb3f03b6da24303/KGTX/CLTX001/3bda8023f4c723e1c8537d8f58c69044.mp3");
//        URL url = new URL("https://m801.music.126.net/20240502223940/841e9ea703035ba63e47057efd406d0e/jdyyaac/obj/w5rDlsOJwrLDjj7CmsOj/35614638290/a412/b8f5/e741/2774ce7eba9908e2fcb5e667bac7f92d.m4a");
        // 2.连接这个资源http
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        FileOutputStream fos = new FileOutputStream("xxx.m4a");
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
    }
}
