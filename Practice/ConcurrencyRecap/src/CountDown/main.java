package CountDown;

import javax.management.DescriptorAccess;
import java.util.concurrent.CountDownLatch;

public class main {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        Waiter waiter = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        new Thread(waiter).start();
        new Thread(decrementer).start();
    }
}
