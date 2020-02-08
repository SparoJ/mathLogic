package com.sparo.math;

import com.sparo.util.Utils;

/**
 * description: 排序算法
 * Created by sdh on 2019-03-27
 */
public class App {
    public static void main(String[] args) {

        int[] arr = {3, 3, 6, 7 , 4, 3, 9, 10, 2 , 3, 1, 5, 0, 8};

        // 测试插入排序
//        IAlgorithmStrategy strategy = new InsertSortStrategy();

//        QuickSortStrategy strategy = new QuickSortStrategy();
        QuickSortStrategyPlus strategy = new QuickSortStrategyPlus();
        strategy.sort(arr);

        App app = new App();
//        app.testAlgorithm(strategy, arr);
        // 测试异或使用
//        app.test();

        //shell 测试
//        ShellSortStrategy sss = new ShellSortStrategy();
//        sss.insertSort(arr);

        //bubble 测试
//        BubbleSortStrategy bss = new BubbleSortStrategy();
//        bss.sort(arr);

        //select 测试
//        SelectSortStrategy selectS = new SelectSortStrategy();
//        selectS.sort(arr);

        // merge 测试
//        MergeSortStrategy mss = new MergeSortStrategy();
//        mss.sort(arr);
        Utils.printIntArray(arr);
    }

    /**
     * 测试异或使用
     */
    public void test() {
        int a = 3;
        int b = 5;
        b = a^b;
        a = a^b;
        b = a^b;
        System.out.print("a->" + a + "b->" + b);
    }

    public void testAlgorithm(IAlgorithmStrategy strategy, int[] arr) {
        int[] result = strategy.sort(arr);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]+"->");
        }

    }
}
