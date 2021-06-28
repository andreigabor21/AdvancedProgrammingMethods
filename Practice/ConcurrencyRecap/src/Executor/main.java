package Executor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(() -> System.out.println("Async task"));
        service.shutdown();

//        Future future = service.submit((Callable) () -> {
//            System.out.println("Asynchronous Callable");
//            return "Callable Result";
//        });
//        System.out.println("future.get() = " + future.get());

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callables = new HashSet<Callable<String>>();
        callables.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Task 1";
            }
        });
        List<Future<String>> futures = executorService.invokeAll(callables);
        for(Future<String> future: futures) {
            System.out.println("future.get= " + future.get());
        }
        executorService.shutdown();


        ExecutorService exe = Executors.newWorkStealingPool();
        List<Callable<String>> callables1 = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");
        exe.invokeAll(callables1)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
    }
}
