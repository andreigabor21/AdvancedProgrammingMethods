package ForkJoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class main {
    public static void main(String[] args) {
        int[] numbers = new int[100];
        Random random = new Random();
        for(int i = 0; i < numbers.length; ++i)
            numbers[i] = random.nextInt(100);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        Integer min = pool.invoke(new FindMin(numbers, 0, numbers.length - 1));
        System.out.println(min);
    }
}
