package ProducerConsumer;

public class Producer extends Thread{

    private CubbyHole cubbyHole;
    private int id;

    public Producer(CubbyHole c, int id) {
        cubbyHole = c;
        this.id = id;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; ++i) {
            for(int j = 0; j < 10; ++j) {
                cubbyHole.put(i,j);
                System.out.println("Producer#" + this.id + "put: ("+i +","+j + ").");
                try {
                    sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
