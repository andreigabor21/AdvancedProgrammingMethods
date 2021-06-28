package written2020_16ian;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class stream {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15,16);
        var res = numbers.stream()
                                    .filter(n -> n % 4 == 0)
                                    .map(n -> (n + 1))
                                    .reduce((a, b) -> (a + b) % 2)
                                    .stream()
                                    .collect(Collectors.toList());
        System.out.println(res);
    }
}
