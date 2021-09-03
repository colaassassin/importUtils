package com.example.demo.test;

public class VolatileDemo {

    public static void main(String[] args) {
        int testCount = 0;

        while (true) {
            final int i = testCount++;
            final Demo1 demo = new Demo1();

            // 判断数据是否初始化完成的线程
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (demo.isDone()) {
                            if (demo.getCount() == 0) {
                                System.out.println(i + "重排错误");
                            }
                            break;
                        }

                        sleep1();
                    }
                }
            }).start();

            // 初始化数据的线程
            new Thread(new Runnable() {
                public void run() {
                    demo.init();
                }
            }).start();

            sleep1();
        }

    }

    private static void sleep1() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Demo1 {
    private boolean done = false;

    private int count = 0;

    public void init() {
        // dosomething
        count = 22222;

        // 数据初始化完成
        done = count > 0;
    }

    public int getCount() {
        return count;
    }

    public boolean isDone() {
        return done;
    }

}