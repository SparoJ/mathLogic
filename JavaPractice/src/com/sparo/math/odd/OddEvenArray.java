package com.sparo.math.odd;

import com.sparo.util.Utils;

/**
 * description: 奇数偶数分离
 * https://blog.csdn.net/wu2304211/article/details/54731194
 * https://blog.csdn.net/u013777382/article/details/77877328
 * Created by sdh on 2019-11-24
 */
public class OddEvenArray {

    public static void main(String[]  args) {
        int[] arr = {1, 4, 7, 9, 6, 3, 2, 0, 5};
        OddEvenArray oea = new OddEvenArray();
        oea.oddEvenIsolate(arr);
    }

    public void oddEvenIsolate(int[] arr) {
        if (arr == null || arr.length<=1) {
            return;
        }
        int oddPoint = 0;
        for (int i = 0; i < arr.length; i++) {
            // &1！=0 奇数（odd）
            if((arr[i]&1)!=0){
//                if((arr[i]%2)!=0){
                Utils.println("position::" + i + "element::" + arr[i] + "oddPointer::" + oddPoint);
                if(oddPoint!=i) { // 相等没必要同元素用临时变量对换
                    int temp = arr[i];
                    arr[i] = arr[oddPoint];
                    arr[oddPoint] = temp;
                }
                oddPoint++;
            }
        }
       Utils.printIntArray(arr);
    }
}
