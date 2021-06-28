package Threads;

public class SimpleThread extends Thread{

    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s\n", threadName, message);
    }

    private static class MessageLoop implements Runnable {
        @Override
        public void run() {
            String importantInfo[] = {"A","B","C","D"};
            try {
                for (int i = 0; i < importantInfo.length; ++i) {
                    //sleep 4 seconds
                    Thread.sleep(4000);
                    //print message
                    threadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long patience = 100 * 60;
        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();
        threadMessage("Waiting for MessageLoop thread to finish");
        while(t.isAlive()) {
            threadMessage("Still waiting...");
            t.join(1000);
            if ((System.currentTimeMillis() - startTime) > patience && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                t.join();
            }
        }
        threadMessage("Finally!");
    }
}
