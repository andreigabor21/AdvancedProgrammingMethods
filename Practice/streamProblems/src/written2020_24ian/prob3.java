package written2020_24ian;

import java.util.ArrayList;

class A implements D{

}
class B extends A implements D {

}

class C extends A implements D {

}

interface D {
}

class Amain{

    D  method1(ArrayList<? extends D> list) {
        if (list.isEmpty())
            return null;
        else
            return list.get(1);}

    void method2(ArrayList<? super C>  list, C elem) {
        list.add(elem);
    }

    void method3(C elem){
        ArrayList<A> listA=new ArrayList<A>(); listA.add(new A());listA.add(new A());
        ArrayList<B> listB = new ArrayList<B>(); listB.add(new B());listB.add(new B());
        ArrayList<C> listC = new ArrayList<C>(); listC.add(new C()); listC.add(new C());

        this.method1(listA); this.method1(listB); this.method1(listC);
        //this.method2(listA,elem); this.method2(listB,elem); this.method2(listC,elem);

    }

}