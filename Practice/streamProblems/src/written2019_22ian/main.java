package written2019_22ian;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
        //a)
        var nums_a = numbers.stream()
                                    .filter(n -> (n%2 == 0 || n %3 == 0))
                                    .map(n -> "A" + ((Integer)(n+1)).toString() + "B")
                                    .reduce((s1, s2) -> s1 + s2)
                                    .orElse(null);
        System.out.println(nums_a);

    }
}
