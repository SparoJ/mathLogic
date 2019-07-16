package com.sparo.math.prime;

/**
 * description:
 * Created by sdh on 2019-06-23
 */
public class Frist50Prime {

    //间接判断一个数是否是素数的方法 获取前置素数 然后看是否可整除 可则非素数
    public static void main(String[] args) {
        int[] primes = new int[50];
        primes[0] = 2;
        int cnt = 1;
        MAIN_LOOP:
        for (int x = 3; x>0 && cnt<primes.length; x++) {
            for (int j = 0; j < cnt; j++) {
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
