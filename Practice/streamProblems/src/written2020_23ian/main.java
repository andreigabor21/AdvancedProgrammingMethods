package written2020_23ian;

import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15);
        var res = numbers.stream()
                                        .filter(n -> (n%2 == 0 || n%5 == 0))
                                        .map(n -> "N" + n.toString() + "R")
                                        .reduce(String::concat)
                                        .orElse(null);
        System.out.println(res);
    }
}
