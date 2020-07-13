package com.sparo.leetcode.greedy;

/**
 * description: description: 题目描述见 dp 中的 股票I
 * see@com.sparo.leetcode.dp.StockI 对比 dp目录下的StockI 贪心解法
 * Created by sdh on 2020-07-13
 */
public class StockI {

    public int maxProfit(int[] prices) {
        return mpGreedy(prices);
    }

    //贪心 局部子问题的最优解构成的全局最优解
    private int mpGreedy(int[] prices) {
        if(prices==null || prices.length <=1) return 0;
        int maxProfit = 0;
        //贪心的思路是从前到后遍历的同时，比较后和之前的最小值，得到当前的最大差值 即为单次操作的最大值
        int boundaryVal = Integer.MAX_VALUE;
        for(int price : prices) {
            if(price<boundaryVal) {
                boundaryVal = price;
            }
            maxProfit = Math.max(maxProfit, price-boundaryVal);
        }
        return maxProfit;
    }
}
