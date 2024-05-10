package com.example.study.thread.syn;

/**
 * 线程不安全
 * 银行取钱
 */
public class UnsafeBank {

    public static void main(String[] args) {
        Account account = new Account("小金库", 100);
        Drawing you = new Drawing(account, 50, "你");
        Drawing girlFriend = new Drawing(account, 100, "girlFriend");

        you.start();
        girlFriend.start();
    }

}

// 账户
class Account {

    String name;

    int money;

    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }

}

class Drawing extends Thread {
    private Account account;

    // 要取的钱
    private int drawingMoney;

    // 手中的现金
    private int cash;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    @Override
    public void run() {
        // 判断账户余额是否充足
        if (account.money - drawingMoney < 0) {
            System.out.println(account.name + "钱不够了");
            return;
        }

        try {
            // 放大问题的发生性
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 取钱
        account.money = account.money - drawingMoney;
        System.out.println(this.getName() + "取了：" + drawingMoney);
        // 手中现金
        cash = cash + drawingMoney;
        System.out.println(account.name + "账户余额：" + account.money);
//        Thread.currentThread().getName() = this.getName()
        System.out.println(this.getName() + "手中现金：" + cash);

    }
}
