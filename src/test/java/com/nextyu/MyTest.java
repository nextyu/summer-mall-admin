package com.nextyu;

/**
 * created on 2017-08-30 17:01
 *
 * @author nextyu
 */
public class MyTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> tl0 = new ThreadLocal<>();
        ThreadLocal<Double> tl1 = new ThreadLocal<>();
        ThreadLocal<Long> tl2 = new ThreadLocal<>();


        tl0.set(1);
        tl1.set(2.1);
        tl2.set(3L);


        System.out.println();
    }
}
