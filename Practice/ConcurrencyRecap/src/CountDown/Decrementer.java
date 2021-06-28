package CountDown;

import java.util.concurrent.CountDownLatch;

public class Decrementer implements Runnable{

    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            latch.countDown();
            Thread.sleep(1000);
            latch.countDown();
            Thread.sleep(1000);
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
