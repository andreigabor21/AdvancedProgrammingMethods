package written2020_20ian;

import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

        var res = numbers.stream()
                                        .filter(n -> (n % 3 == 0 || n % 2 == 0))
                                        .map(n -> "A" + n.toString() + "B")
                                        .reduce(String::concat)
                                        .orElse(null);
        System.out.println(res);
    }
}
