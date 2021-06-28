package Main;


import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lambdas {

    public static void main(String[] args) {

        //FILTERING
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
        List<Integer> twoEvenSquares =
                numbers.stream()
                        .filter(n-> {System.out.println("filtering" + n); return n%2==0;})
                        .map(n -> {System.out.println("mapping" + n); return n*n;})
                        .limit(2)
                        .collect(Collectors.toList());
        System.out.println('\n');

        //MAPPING
        List<String> words = Arrays.asList("Oracle", "Java", "Magazine");
        List<Integer> wordLengths =
                words.stream()
                        .map(String::length)
                        .collect(Collectors.toList());
        System.out.println(wordLengths);
        System.out.println('\n');

        //Stream.min() and Stream.max()
        List<String> items = Arrays.asList("asd","qwer","qwert");
        String shortest = items.stream()
                                .min(Comparator.comparing(item -> item.length()))
                                .get();
        System.out.println(shortest);

        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);

        //Stream.count()
        long count = items.stream()
                            .filter(item -> item.startsWith("q"))
                            .count();
        System.out.println(count);

        //Stream.reduce()
        String reduced = items.stream()
                                .reduce((acc, item) -> acc + "" +item)
                                .get();
        System.out.println(reduced);

        String reduced2 = items.stream()
                                .filter(item -> item.startsWith("q"))
                                .reduce("",(acc, item) -> acc + " " + item);
        System.out.println(reduced2);

        int sum = numbers.stream()
                            .reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        int max = numbers.stream()
                .reduce(0, Integer::max);
        System.out.println(max);

        //Numerical Streams
        IntStream oddNumbers = IntStream.rangeClosed(10, 30)
                                        .filter(n -> n%2 == 1);
        int[] nrs = {1, 2, 3, 4};
        IntStream numbersFromArray = Arrays.stream(nrs);
        //file into a stream of lines
//        long numberOfLines =
//                Files.lines(Paths.get("file"), Charset.defaultCharset())
//                        .count();

        Stream<Integer> nr = Stream.iterate(0, n -> n + 10);

        //Processing Order
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {System.out.println("map: " + s);return s.toUpperCase();})
                .filter(s -> {System.out.println("filter: " + s);return s.startsWith("A");})
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println("\n");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {System.out.println("filter: " + s);return s.startsWith("a");})
                .map(s -> {System.out.println("map: " + s);return s.toUpperCase();})
                .forEach(s -> System.out.println("forEach: " + s));

        //Reusing streams
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("‐ d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        streamSupplier.get().anyMatch(s -> true); // ok
        streamSupplier.get().noneMatch(s -> true); // ok

    }


}
