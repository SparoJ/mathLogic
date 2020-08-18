package com.sparo.leetcode.dp;

/**
 * description:
 * see @com.sparo.leetcode.greedy.StockI 对比 贪心目录下的StockI 贪心解法
 * 此处为 dp通用解法 ：
 * 121. 买卖股票的最佳时机
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 *
 * Created by sdh on 2020-07-13
 */
public class StockI {

    public int maxProfit(int[] prices) {
        return mp(prices);
    }

    private int mp(int[] prices) {

        //动态规划
        /*
         * 1. 3维动态转移方程--
         * dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-1][k][1]+prices[i])
         * dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-1][k-1][0]-prices[i]);
         * 2. 3维转2维过程：
         * dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1]+prices[i]);
         * dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0]-prices[i]);
         * base case 没有交易次数 收益为0
         * dp[i-1][0][0] = 0;
         * k=1 移除共性影响
         * dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]+prices[i]);
         * dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
         * 3. 边界base case i=0, i-1=-1
         * dp[-1][0] = 0; dp[-1][1] = Integer.MIN_VALUE;
         * 前者表征还未开始交易 收益为0，后者表示未开始交易不可能存在持有状况用负无
         * 穷表示不可能性
         * 4. 二维转变量声明持有
         *  关联变量只有 两个 用 dp_i_0 和 dp_i_1 表示，dp[i-1][0] 为dp[i][0]的前一次收益
         * dp_i_0 = Math.max(dp_i_0, dp_i_i+prices[i]);
         * dp_i_1 = Math.max(dp_i_1, -prices[i]);
         */
        //base case 声明
        int dp_i_0 = 0; int dp_i_1 = Integer.MIN_VALUE;
        for(int i = 0; i<prices.length; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1+prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        //返回未持有的状态
        return dp_i_0;
    }
}
