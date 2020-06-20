package com.sparo.math.base;

import com.sparo.math.QuickSortStrategyPlus;
import com.sparo.util.Utils;

/**
 * description:
 * Created by sdh on 2020-02-07
 */
public class TopK {

    public static void main(String[] args) {
//        int[] arr = {3, 3, 6, 7 , 4, 3, 9, 10, 2 , 3, 1, 5, 0, 8};
        int[] arr = {3,2,1,5,6,4};
        TopK tk = new TopK();
       int k =  tk.quickSelect(arr, 0, arr.length-1, 2);
       System.out.println("topK->" + k);

       int[] sort = arr.clone();
        QuickSortStrategyPlus qssp = new QuickSortStrategyPlus();
        Utils.printIntArray(qssp.sort(sort));

//        0->1->2->3->3->3->3 ->4->5->6->7->8->9->10-
    }

    int Partition(int A[], int left, int right)
    {
        int temp = A[left]; //将A[left]存放至临时变量temp
        while(left<right)  //只要left与right不相遇
        {
            while(left<right && A[right]<=temp) //反复左移right
                right--;
//            if(left<right) {}
            A[left] = A[right];	//将A[right]挪到A[left]
            while(left<right && A[left]>=temp)	 //反复右移left
                left++;
//            if(left<right){}
            A[right] = A[left];	//将A[left]挪到A[right]
        }

        A[left] = temp;	//把temp放到left与right相遇的地方
        System.out.println("partation 一轮后数组->index->" + left);
        Utils.printIntArray(A);
        return left;	//返回相遇的下标
    }

    //快速选择算法，从A[left,right]中返回第k大的数
    int quickSelect(int A[], int left, int right, int k)
    {
        if(left==right)
            return A[left];	//边界

        int p = Partition(A, left, right);	//划分后主元的位置为p

        int m = p-left+1;	//A[p]是A[left,right]中的第M大

        if(k==m)	//找到第k大的数
            return A[p];
        if(k<m)		//第k大的数在主元左侧
            return quickSelect(A, left, p-1, k);		//往主元左侧找第k大
        else		//第k大的数在主元右侧
            return quickSelect(A, p+1, right, k-m);	//往主元右侧找第k-m大
    }

}
