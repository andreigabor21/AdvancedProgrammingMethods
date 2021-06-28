package Pizzerie;

import java.util.Map;

public class PizzaBoom {
    private Integer[] quantitySold;
    private Pizza[] menu;

    public PizzaBoom(Integer size) {
        quantitySold = new Integer[size];
        menu = new Pizza[size];
        Pizza p = new Pizza();
        System.out.println(p.getVariety() + " " + p.getSize());
    }

    public static void main(String[] args) {
        PizzaBoom pb = new PizzaBoom(5);

        ////////////
    }
}

class Pizza {
    private char variety;
    private int size;

    public char getVariety() {
        return variety;
    }

    public int getSize() {
        return size;
    }

    /* public Pizza(String variety, Integer size) {
        this.variety = variety;
        this.size = size;
    }*/
}