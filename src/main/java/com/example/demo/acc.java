package com.example.demo;


public class acc {

    final static Object obj1 = new Object();

    public static void main(String[] args) {
        Thread2 thread2 = new Thread2();

        thread2.start();

        for (int i = 0; i < 30; i++) {
            System.out.println("输出C" + i);
        }
        

        synchronized (obj1){
            try {
                System.out.println("我先执行了");
                for (int i = 0; i < 30; i++) {
                    System.out.println("输出A" + i);
                }
                obj1.wait();
                System.out.println("我已经重新呗唤醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

class Thread2 extends Thread{

    @Override
    public void run() {
        System.out.println("ThreadB is Running~~~~~~~~~");
        synchronized (acc.obj1){
            for (int i = 0; i < 10; i++) {
                System.out.println("输出B" + i);
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            acc.obj1.notify();
        }
    }
}


