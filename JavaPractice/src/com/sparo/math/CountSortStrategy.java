package com.sparo.math;

import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2020-02-05
 */
public class CountSortStrategy {

    //计数排序
    static int countV = 0;
    public static int[] countSort(int[] arr) {
        int arrLength = arr.length;
        //1. 获取arr 数组中元素的最大和最小值
        int maxVal = arr[0]; // 最大最小值不能直接赋值为0！！！ 或者其他个人认为是小值的值！！！或者最大值的值，这里取任意一个arr 中的值即可
        int minVal = arr[0];
        for (int i = 0; i < arrLength; i++) {
            maxVal = maxVal>=arr[i]?maxVal:arr[i];
            minVal = minVal<=arr[i]?minVal:arr[i];
        }
        System.out.println("maxVal->" + maxVal + "->minVal->" + minVal) ;
        //2. 根据gap 创建 count 数组并将元素出现的次数作为count 数组的值save，count 脚标指向对应元素
        int gap = maxVal - minVal; // 0-9 9-0=9 length = 9-0+1 = 10
        int[] count = new int[gap+1];
        for (int i = 0; i < arrLength; i++) {
            count[arr[i]-minVal]++; // 非count[i] !!! 含义完全不同
        }
        //3. 变换出现次数的count 数组，转换成脚标指向元素在sort后的数组中该处的位置
//        int sum = 0;
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i-1];
            countV++;
//            System.out.println("count(" + count[i] + ")" + "->i(" + i + ")" );
        }
        System.out.println("countV->" + countV);
        //4. 创建sortArray 排序数组，根据count数组来排序
        int[] sortedArray = new int[arr.length];
        for(int i = sortedArray.length -1; i>=0; i--) {
            //元素-> 脚标-> 脚标内元素值的关系转换
              sortedArray[count[arr[i]-minVal]-1] = arr[i];
              //减1，前移
              count[arr[i]-minVal]--;
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] arr = {95, 94, 91, 98, 99, 90, 99, 93, 91, 92, 90, 94, 96};
        int[] sorted = countSort(arr);
        Utils.printIntArray(sorted);
    }
}
