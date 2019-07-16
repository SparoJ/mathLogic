package com.sparo.math.prime;

import java.util.Scanner;

/**
 * description: 判断是否是素数
 * Created by sdh on 2019-06-23
 */
public class IsPrime {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            if (!in.hasNextInt()) {
                break;
            }
            isPrime2(in.nextInt());
        }
    }


    public static void isPrime1(int num) {
        boolean isPrime = true; //初始化声明 num的状态 默认是 不符合条件则置为 false
        if (num == 1) {
            isPrime =  false; //1非质数（素数）
        }
        for (int i = 2; i < num; i++) { //for 循环执行方式 1 初始化 2 判断条件是否满足 满足则执行循环体否则结束循环 3 执行上一次循环后 完成++运算 再去判断条件是否满足 执行以上2 循环体 ++和判断条件的循环
            if(num % i == 0) { // <num则直接根据条件 去掉了声明 素数自身
                isPrime = false;
            }
        }
        printMsg(num, isPrime);
    }

    private static void printMsg(int num, boolean isPrime) {
        if (isPrime) {
            System.out.println(num + "：是素数");
        } else {
            System.out.println(num + "：不是素数");
        }
    }

    // 复杂度 O(sqrt(n))
    public static void isPrime2(int num) {
        boolean isPrime = true;
        if (num == 1 || num%2 == 0 && num!=2) {
            isPrime = false;
        }
        //1 i 从3开始 跳过2 2是最小素数
        //2 取方根
        //3 i逐步+2 因为偶数前面已经过滤 这里只考虑奇数num的素数判断
        for (int i = 3; i <= Math.sqrt(num); i=+2) {
            if( num%i == 0) {
                isPrime = false;
                break;
            }
        }
        printMsg(num, isPrime);
    }

}
