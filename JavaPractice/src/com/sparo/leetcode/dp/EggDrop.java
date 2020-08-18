package com.sparo.leetcode.dp;

import com.sparo.util.Utils;

class EggDrop {

    public static void main(String[] args) {
        superEggDrop(2, 6);
    }

    public static int superEggDrop(int K, int N) {

        int count = 0;
        count =  sedDpNormalRecursion(K, N);
        Utils.printWithTag("sedDpNormalRecursion-count>", count);
        count = sed(K, N);
        Utils.printWithTag("sed-count>", count);
        count = sedDpNormalTraversal(K, N);
        Utils.printWithTag("sedDpNormalTraversal-count>", count);
        count = sedReverseMind(K, N);
        Utils.printWithTag("sedReverseMind-count>", count);

        return count;
    }



    private static int sedReverseMind(int K, int N) {
            int[][] dp = new int[K + 1][N + 1];
            for (int m = 1; m <= N; m++) {
                dp[0][m] = 0; // zero egg
                for (int k = 1; k <= K; k++) {
                    dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
                    System.out.println("k->" + k + "->m->" + m + "->km->" + dp[k][m] +
                            "\nkm-1->" + dp[k][m-1]
                    +"->k1m->" + dp[k-1][m-1]);
                    if (dp[k][m] >= N) {
                        return m;
                    }
                }
            }
            return N;
    }

    //常规dp方式 枚举 o(kn*n)
    private static int sedDpNormalRecursion(int K, int N) {
        //base case 健壮性
        // if(N==0) return 0;
        // if(K==1) return N;

        //状态量：鸡蛋数/楼层数
        //选择 在i楼层扔一个鸡蛋 碎or未碎
        //创建memo数组
        int[][] memo = new int[K+1][N+1];
        return sedRecursion(K, N, memo);
    }

    private static int sedRecursion(int K, int N, int[][] memo) {
        //精简base case 如下：当楼层为0/1 or 鸡蛋数为1时
        if(N == 0 || N == 1|| K == 1) return N;
        //❌ 记得使用memo 否则 备忘录数组存在毫无意义
        if(memo[K][N]!=0) return memo[K][N];
        memo[K][N] = N; //见第二处标记 ❌
        for(int i =2; i <= N; i++) { // o(N)
            //对于每次i层的选择，都会存在一次max 然后在所有的max值序列中求最小值即为最小移动次数，另外别忘了+1
            //❌ 错误的点还是会犯错，未深刻理解比较大小时的陷阱 初始变量对比较结果的影响
            memo[K][N] = Math.min(memo[K][N],
                    Math.max(sedRecursion(K-1, i-1, memo), //  o(K)
                            sedRecursion(K, N-i, memo))+1); // o(N)
        }
        return memo[K][N];
    }

    private static int sedDpNormalTraversal(int K, int N) {
        int[][] middleResults = new int[K + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            middleResults[1][i] = i; // only one egg
            middleResults[0][i] = 0; // no egg
        }
        for (int i = 1; i <= K; i++) {
            middleResults[i][0] = 0; // zero floor
        }

        for (int k = 2; k <= K; k++) { // start from two egg
            for (int n = 1; n <= N; n++) {
                int tMinDrop = N * N;
                for (int x = 1; x <= n; x++) {
                    tMinDrop = Math.min(tMinDrop, 1 + Math.max(middleResults[k - 1][x - 1], middleResults[k][n - x]));
                }
                middleResults[k][n] = tMinDrop;
            }
        }

        return middleResults[K][N];
    }

    //常规dp方式+二分搜索优化一层for 降低复杂度为o(kn*lgn)
    private static int sedDpBinarySearch(int K, int N) {
        return 0;
    }

    //优化版 更新角度去dp o(kn) 可尝试再二分改良为o(klgn)
    private static int  sedDpOptimize(int K, int N) {
        return 0;
    }

//    public static int superEggDrop(int K, int N) {

        // int i = Integer.MAX_VALUE;
        // int j = 0;
        // for(; j<i; j++) {
        //     if(Math.pow(2,j)>N) break;
        // }
        // return j;
//        return sed(K, N);
//    }

    private static int sed(int K, int N) {
        // Right now, dp[i] represents dp(1, i)
        int[] dp = new int[N+1];
        for (int i = 0; i <= N; ++i)
            dp[i] = i;

        for (int k = 2; k <= K; ++k) {
            // Now, we will develop dp2[i] = dp(k, i)
            int[] dp2 = new int[N+1];
            int x = 1;
            for (int n = 1; n <= N; ++n) {
                // Let's find dp2[n] = dp(k, n)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[n-x]) > max(dp[x], dp2[n-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (x < n && Math.max(dp[x-1], dp2[n-x]) > Math.max(dp[x], dp2[n-x-1]))
                    x++;

                // The final answer happens at this x.
                dp2[n] = 1 + Math.max(dp[x-1], dp2[n-x]);
            }

            dp = dp2;
        }

        return dp[N];
    }
}