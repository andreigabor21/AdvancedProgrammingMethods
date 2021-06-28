package ProducerConsumer;

public class main {
    public static void main(String[] args) {
        CubbyHole c = new CubbyHole();
        Producer p = new Producer(c, 1);
        Consumer cons = new Consumer(c, 1);
        p.start();
        cons.start();
    }
}
