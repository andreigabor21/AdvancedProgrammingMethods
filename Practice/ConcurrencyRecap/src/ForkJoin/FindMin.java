package ForkJoin;

import java.util.concurrent.RecursiveTask;

public class FindMin extends RecursiveTask<Integer> {
    private int[] numbers;
    private int startIndex;
    private int endIndex;

    public FindMin(int[] numbers, int startIndex, int endIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Integer compute() {
        int sliceLength = (endIndex - startIndex) + 1;
        if(sliceLength > 2) {
            FindMin lowerfindMin = new FindMin(numbers, startIndex, startIndex + (sliceLength / 2) - 1);
            lowerfindMin.fork();
            FindMin upperfindMin = new FindMin(numbers, startIndex + (sliceLength / 2), endIndex);
            upperfindMin.fork();
            return Math.min(lowerfindMin.join(), upperfindMin.join());
        }
        else
            return Math.min(numbers[startIndex], numbers[endIndex]);
    }
}
