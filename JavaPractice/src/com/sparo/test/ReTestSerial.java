package com.sparo.test;

import com.sparo.util.Utils;

import java.util.Random;

/**
 * description: 手写测试类
 * Created by sdh on 2020-02-08/2-12
 */
public class ReTestSerial {

    public static void main(String[] args) {
        // 手写快排测试
        int[] arr = {3, 3, 6, 7 , 4, 3, 9, 10, 2 , 3, 1, 5, 0, 8};
        ReTestSerial rts = new ReTestSerial();
        /*rts.quickSort(arr,0, arr.length-1);
        Utils.printIntArray(arr);*/

        // 随机数测试
    /*    for (int i = 0; i < 100; i++) {
            int randX = createRandomFromLeftToRight(3, 10);
            System.out.print(randX + "->");
        }*/

        // 获取数组中 第k大 元素所在位置（顺便伪排序）
        Utils.printIntArray(arr);
        int index = rts.obtainTheKIndex(arr, 0, arr.length-1, 3);
//        int index = rts.quickSelect(arr, 0, arr.length-1, 1);
//        int index = getMaxK(arr, 0, arr.length-1, 6);
        Utils.printIntArray(arr);
        System.out.println("val->" + index);
    }


    private static int count=1;//  大于index 点大的点数 index-low+1
    private static int getMaxK(int [] array,int low ,int hign,int k)
    {
        if(low> hign)
            return -1;
        int index=getMaxKPartition(array, low, hign);
        count=index-low+1;// 比基点大 的 点个数
        if(count>k)
        {
            return getMaxK(array, low, index-1, k);

        }
        else if( count < k )
        {    // 点在后半部分
            return getMaxK(array, index+1,hign,k-count);
        }
        else {

            return array[index];
        }
    }

    // 求第k大
    public int obtainTheKIndex(int[] arr, int left, int right, int k) {
        if(left == right) {
            return arr[left];
        }
        int index = partition(arr, left, right); // 返回的是脚标
        System.out.println("index->>>" + index + "->k->" + k);
        Utils.printIntArray(arr);
        int p = index-left+1; // 转换后的position 第x
        if(p == k) {
            System.out.println("p----" + p + "arr[p]->" + arr[p]);
            return arr[index]; // arr[p];
        } else
        if(p<k) {
            return obtainTheKIndex(arr, index+1, right, k-p);
        }else {
            return obtainTheKIndex(arr, left, index-1, k);
        }
    }


    public static int getMaxKPartition(int [] a,int low ,int high)
    {
        /**快排核心算法 先移动末指针 在移动首指针 直到 i>j
         * 大*/
        int i=low,j=high,temp=a[low];
        if(low < high)
        {
            while(i<j)
            {
                while(i<j && temp>=a[j])
                    j--;
                if(i<j)// 双重验证
                {
                    a[i]=a[j];
                    i++;
                }
                while(i<j && temp<=a[i])
                    i++;
                if(i<j)
                {
                    a[j]=a[i];
                    j--;
                }
            }
            a[i]=temp;
        }
        return i;
    }



    //对区间[left,right]进行划分
    static int Partition(int nums[], int start, int end)
    {
        int left=start;
        int right=end;
        int pivot=nums[start];
        while(left<right){
            while(left<right&&nums[right]>=pivot)
                right--;
            if(left<right){
                nums[left]=nums[right];
                left++;
            }
            while(left<right&&nums[left]<pivot)
                left++;
            if(left<right){
                nums[right]=nums[left];
                right--;
            }
        }
        nums[left]=pivot;
        return left;
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


    private static int createRandomFromLeftToRight(int left, int right) {
        if (left>=right) {
            return -1;
        }
        Random r = new Random();
        int gap = right - left+1;
        int res = r.nextInt(gap)+left;
        return res;
    }


// 什么是快排？ 快速排序的核心是partition 即获取分解点的位置然后进行分治
//** 递归 + 分治（前为算法实现策略 后为 算法思想）

    public void quickSort(int[] arr, int left, int right) {
        if(arr == null || left>=right) {
            return;
        }
//        partition(arr, left, right);
        int pivotIndex = ranPartition(arr, left, right);
        quickSort(arr, left, pivotIndex-1);
        quickSort(arr, pivotIndex+1, right);
    }

    // 非随机快排/随机选择 --- 修改为 逆序排
    public static int partition(int[] arr, int left, int right) {
        /**
         *  1. 设定锚点元素 构建循环直至一侧比锚点元素大另一次比锚点元素小（具体根据需求场景来实现）
         *         如果要通过该方法得到优化的topK o(n) 的复杂度 建立降序的partition 方法，即 左侧为大，右侧为小
         *         降序排序同样， 如果正常升序排序则 左侧小，右侧大
         *         这里先实现常规的升序 然后逆序 分别交付 排序和 随机选择使用
          */
        int pivot = arr[left];
        // 采用 填坑法  来实现
        while(left < right) {

            while(left<right&& arr[right]<=pivot) {
                right--;
            }
            if(left<right) {
                arr[left] = arr[right];
                left++;
            }
            while(left<right && arr[left]>=pivot) {
                left++;
            }
            if(left<right) {
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = pivot;
        return left;
    }

    public int ranPartition(int[] arr, int left, int right) {
        //如果对于锚点元素不做筛选容易产生效率最差化o(n^2) 	随机选择锚点位置并交换元素来实现效率规避
        // 随机选择 从left 到 right 的 位置中的一个
        int randX = createRandomFromLeftToRight(left, right);
        int pivot = arr[randX];
        arr[randX] = arr[left];
        arr[left] = pivot;
        return partition(arr, left, right);
    }


}
