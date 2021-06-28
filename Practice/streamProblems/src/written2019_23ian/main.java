package written2019_23ian;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

        var str = numbers.stream()
                                    .filter(n -> n % 4 == 0)
                                    .map(n -> (n+1))
                                    .reduce((a, b) -> (a+b) % 2)
                                    .stream().collect(Collectors.toList());

        System.out.println(str);

    }
}
