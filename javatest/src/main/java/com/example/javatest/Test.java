package com.example.javatest;

public class Test {


    public static void main(String[] args) {
        MyThread myThread = new MyThread() ;
        myThread.start();
        try {
            myThread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.interrupt();
        try {
            myThread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class MyThread extends Thread{

        @Override
        public void run() {
                System.out.println("hello");
        }
    }


}