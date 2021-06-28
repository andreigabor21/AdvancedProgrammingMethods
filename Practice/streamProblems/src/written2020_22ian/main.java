package written2020_22ian;

import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);

        var res = numbers.stream()
                                    .filter(n -> (n % 4 == 0))
                                    .map(n -> (n+1))
                                    .reduce((a, b) -> (a + b) % 2)
                                    .orElse(null);
        System.out.println(res);
        //4 8 12 -> 5 9 13
    }
}
