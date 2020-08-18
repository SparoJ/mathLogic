package com.sparo.java.basic;

/**
 * description: 静态分派，方法重载
 * Created by sdh on 2020-05-28
 * https://blog.csdn.net/ns_code/article/details/17965867
 * https://blog.csdn.net/fly_leopard/article/details/79038414
 *
 * 结果为 三次 ff，
 */
public class TestStatic {
    //hi 方法重载
    public void hi(FatherS f, FatherS f1) {
        System.out.println("ff");
    }

    public void hi(FatherS f, SonS s) {
        System.out.println("fs");
    }

    public void hi(SonS s, SonS s2) {
        System.out.println("ss");
    }

    public void hi(SonS s, FatherS f) {
        System.out.println("sf");
    }

    public static void main(String[] rags) {
        FatherS f = new FatherS();
        FatherS s = new SonS();
        TestStatic t = new TestStatic();
        t.hi(f, new FatherS());
        t.hi(f, s);
        t.hi(s, f);
    }
}
class FatherS {
}

class SonS extends FatherS {
}
