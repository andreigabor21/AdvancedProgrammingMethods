package written2020_20ian;

import java.lang.reflect.Array;
import java.util.ArrayList;

class A {
}
class B extends A {
}
class C extends A {
}

class ClassMethods {
    A method1(ArrayList<? extends A> list) {
        return list.get(1);
    }
    void method2(ArrayList<? super A> list, A el) {
        list.add(el);
    }

    void method3(A elem) {
        ArrayList<A> listA = new ArrayList<A>();
        listA.add(new A()); listA.add(new A());
        ArrayList<B> listB = new ArrayList<B>();
        listA.add(new B()); listB.add(new B());
        ArrayList<C> listC = new ArrayList<C>();
        listC.add(new C()); listC.add(new C());

        this.method1(listA);
        this.method1(listB);
        this.method1(listC);

        this.method2(listA, elem);
//        this.method2(listB, elem);
//        this.method2(listC, elem);
    }
}
