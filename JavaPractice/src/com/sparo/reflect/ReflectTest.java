package com.sparo.reflect;

import java.lang.reflect.Array;

/**
 * description: 反射测试类
 * Created by sdh on 2020-04-11
 */
public class ReflectTest {

    public static void main(String[] args) {
//        testReflectArray();
    }



    /**
     *   1.只有数组才能通过 getComponentType 方法获取 class 的type
     *   2. int[] a a 对应 type 为 int
     *   3. Integer[] inte 对应type 为 java.lang.Integer
     *   4. Array reflect 类的使用
     *   5. 未知对象或者数组对象类型时，可以 通过 getClass 获取对应对象的类型
     *   可以通过该类动态创建数组，并修改数组和获取数组中的元素而不需要强转
     */
    public static void testReflectArray() {
        try {
            Class<?> componentType = Integer.class.getComponentType();
            Class<?> componentTYPE = Integer.TYPE.getComponentType();
            int[] a = new int[3];
            Integer[] inte = new Integer[3];
            Class<?> aType = a.getClass().getComponentType();
            Class<?> iType = inte.getClass().getComponentType();
            System.out.println("Integer.class componentType->"+ (componentType) + "Integer.TYPE->componentTYPE->" + componentTYPE+
                    "\n->Integer.TYPE->"+ Integer.TYPE+"->aType"+aType + "->iType"+ iType);
            Object o = Array.newInstance(Integer.class, 5);
            Object os = Array.newInstance(int.class, 5);
            Object osN = Array.newInstance(a.getClass().getComponentType(), 5);
            System.out.println("o->"+ o.toString() + "->os->" + os + "->osN->" + osN);
            Array.set(o, 4, 20);
            Integer[] integers = (Integer[]) o;
            for (int i = 0; i < integers.length; i++) {
                System.out.println("integers i->" + integers[i]);
            }
        } catch (NegativeArraySizeException e) {
            e.printStackTrace();
        }
    }
}
