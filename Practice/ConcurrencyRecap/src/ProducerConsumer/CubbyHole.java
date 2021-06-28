package ProducerConsumer;

public class CubbyHole {
    private int x, y;
    private boolean available = false;

    public synchronized int get() {
        while(available == false) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            available = false;
            notifyAll();
            return x+y;
    }

    public synchronized void put(int i, int j) {
        while(available == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        x = i;
        y = j;
        available = true;
        notifyAll();
    }
}
