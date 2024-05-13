package com.example.study.thread.comm;

public class TestProducerConsumerDemo02 {
    public static void main(String[] args) throws InterruptedException {
        Novel novel = new Novel();
        new Thread(new Writer(novel)).start();
        new Thread(new Reader(novel)).start();
    }
}

class Writer implements Runnable {

    Novel novel;

    public Writer(Novel novel) {
        this.novel = novel;
    }


    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            this.novel.write("作者写了第" + i + "章");
        }
    }
}

class Reader implements Runnable {
    Novel novel;

    public Reader(Novel novel) {
        this.novel = novel;
    }
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            this.novel.read();
        }
    }
}

class Novel {
    String chapter; //小说章节

    boolean flag = true;

    // 写小说
    public synchronized void write(String chapter) {
        // 还没写好，等待
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("作者写好了新的章节：" + chapter);
        // 通知读者阅读
        this.notifyAll();
        this.chapter = chapter;
        this.flag = !this.flag;

    }

    // 读小说
    public synchronized void read() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("读者看完了最新章节，催更！！！");
        this.notifyAll();
        this.flag = !this.flag;
    }
}

