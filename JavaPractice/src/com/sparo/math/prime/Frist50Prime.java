package com.sparo.math.prime;

import java.util.Arrays;

/**
 * description:
 * Created by sdh on 2019-06-23
 */
public class Frist50Prime {

    public static int count = 0;

    //间接判断一个数是否是素数的方法 获取前置素数 然后看是否可整除 可则非素数
    public static void main(String[] args) {

//        primeA(120);
//        primeB(1200000);
        int num = countPrimesC(120000);
//        System.out.println("num->>" + num);
        System.out.println("count->>" + count);
    }

    private static void primeB(int maxNum) {
        countPrimes(maxNum);
    }

    static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime(i)) count++;
        return count;
    }

    // 判断整数 n 是否是素数
    static boolean isPrime(int n) {
        for (int i = 2; i*i < n; i++) {
            count++;
            if (n % i == 0)
                // 有其他整除因子
                return false;
        }
        return true;
    }

    static int countPrimesC(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);

        for (int i = 2; i*i < n; i++)
            if (isPrim[i]) {
                // i 的倍数不可能是素数了
                for (int j = 2 * i; j < n; j += i) {
                    count++;
                    isPrim[j] = false;
                }
            }
//        for (int i = 0; i < isPrim.length; i++) {
//            System.out.print("->" + isPrim[i]);
//        }
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }


    private static void primeA(int maxNum) {
        int[] primes = new int[40];
        primes[0] = 2;
        int cnt = 1;
        MAIN_LOOP:
        for (int x = 3; x>0 && cnt<primes.length && x<=maxNum; x++) {
            for (int j = 0; j < cnt; j++) {
                count++;
                if (x%primes[j]==0) {
                    continue MAIN_LOOP;
                }
            }
            primes[cnt++] = x;
        }
        for (int k :
                primes) {
            System.out.print(k + " "); //ln 换行 不带不换行
        }
    }
}
