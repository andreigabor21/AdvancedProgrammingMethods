package written2019_17ian;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class stream {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15,16);
        var res = numbers.stream()
                                    .filter(n -> n % 4 == 0)
                                    .collect(Collectors.toList());
        System.out.println(res);
    }
    Integer a = 2;
    /* what is a JAVA THREAD
    -a thread is a particular execution path of a
    process.
    - one allows multiple threads to read and write the
    same memory (no process can directly access
    the memory of another process).
    -when one thread modifies a process resource,
    the change is immediately visible to sibling
    threads.
     */
}
