package written2016_19ian;

import java.util.ArrayList;

public class wild {
    public static void main(String[] args) {
//        ArrayList<?> wildlist;
//        ArrayList<Integer> intlist;
//        wildlist = intlist;
//        intlist.add(1, new Integer(2));
//        wildlist.add(1, new Integer(2));
//        Integer a = intlist.get(1);
//        Integer b = wildlist.get(1);
//
        ArrayList<? super Integer> wildlist;
        ArrayList<Integer> intlist = new ArrayList<>();
        wildlist = intlist;
        intlist.add(2);
        wildlist.add(2);
        Integer a = intlist.get(1);
        Integer b = (Integer) wildlist.get(1);


        /* PROTECTED ACCESS MODIFIER
        The fields and methods which are declared protected are visible inside the
        class, inside the derived classes and inside the same package.

         */

    }
}
