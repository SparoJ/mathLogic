package com.sparo.test;

/**
 * description:
 * Created by sdh on 2020-04-18
 */
public class GenericType {

    public static void main(String[] args) {
        B b = new B();
        b.get();
        A a = new A();
        a.get();
    }

    public static class A {
        Father get() {
            return null;
        }
    }

    public static class B extends A {
        @Override
        Son get() {
            return null;
        }
        // not allow
//        Father get() {
//            return null;
//        }

    }

    public static class Father {

    }

    public static class Son extends Father {
    }

}
