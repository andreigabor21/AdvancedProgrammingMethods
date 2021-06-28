package CountDown;

import java.util.concurrent.CountDownLatch;

public class Waiter implements Runnable{

    CountDownLatch lath = null;

    public Waiter(CountDownLatch lath) {
        this.lath = lath;
    }

    @Override
    public void run() {
        try {
            lath.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter released");

    }
}
