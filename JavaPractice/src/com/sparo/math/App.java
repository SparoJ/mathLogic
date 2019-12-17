package com.sparo.math;

/**
 * description: 排序算法
 * Created by sdh on 2019-03-27
 */
public class App {
    public static void main(String[] args) {

        int[] arr = {3, 3, 6, 7 , 4, 3, 9, 10, 2 , 3, 1, 5, 0, 8};

        // 测试插入排序
        IAlgorithmStrategy strategy = new InsertSortStrategy();

//        QuickSortStrategy strategy = new QuickSortStrategy();
//        QuickSortStrategyPlus strategy = new QuickSortStrategyPlus();

        App app = new App();
        app.testAlgorithm(strategy, arr);
    }

    public void testAlgorithm(IAlgorithmStrategy strategy, int[] arr) {
        int[] result = strategy.sort(arr);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]+"->");
        }

    }
}
