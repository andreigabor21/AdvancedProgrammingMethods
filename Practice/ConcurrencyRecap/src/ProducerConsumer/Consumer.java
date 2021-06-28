package ProducerConsumer;

public class Consumer extends Thread{
    private CubbyHole cubbyHole;
    private int id;

    public Consumer(CubbyHole c, int id) {
        cubbyHole = c;
        this.id = id;
    }

    @Override
    public void run() {
        int value = 0;
        for(int i = 0; i < 10; ++i) {
            value = cubbyHole.get();
            System.out.println("Consumer#" + this.id + " got: " + value);
        }
    }
}
