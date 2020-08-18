package com.sparo.test.review.sort;

import com.sparo.util.Utils;

/**
 * description: 测试TopK 各种实现方案 以及延伸问题 求数组中第k大元素
 * Created by sdh on 2020-03-08
 */
public class TestTopKTheKSerial {

    //1.全排/冒泡
    //2.取k数堆排 然后从k+1 往后和k中数对比
    //

    public  static void main(String[] args) {
//        int[] arr = {3,2,3,1,2,4,5,5,6};
        int[] arr = {7,6,5,4,3,2,1};
        TestTopKTheKSerial ttt = new TestTopKTheKSerial();
        ttt.heapify(arr, 5);
        Utils.printIntArray(arr);
    }

    /**
     *第k大 使用堆排序解决，思路为 建立包含k个数元素的最小堆，堆顶
     *root 元素为对应当前第k大元素，所有元素在replace + shiftDown
     *后，root即为数组第k大， 复杂度 nlgk/o(1)
     */
    //这里不是所有元素堆化，是先取前k元素堆化，再处理
    public void heapify(int[] nums, int k) {

        int length = nums.length;

//        for(int i = length-1; i >=0; i--) {
//            // if(i<k) {
//            //     shiftDown(nums, );
//            // }
//            // 最小堆不满足 k个元素时，直接下沉，大于k个元素时，比较root元素，大于root则替换下沉
//            if(i<=length-k-1) {
//                if(nums[i]>nums[0]) {
//                    nums[0] = nums[i];
//                } else {
//                    continue;
//                }
//            }
//            //第k大
//            shiftDown(nums, i, k-1);
//            System.out.println("每次 shift Down->");
//                    Utils.printIntArray(nums);
//
//        }
        //构建k个元素的小顶堆, 最后元素脚标为 k-1  （x-1)/2, 下沉到的脚标位置为k-1
        for(int i = k/2-1; i>=0; i--) {
            shiftDown(nums, i, k-1);
        }
        //从k/2-1起到length-1,比较元素大小, 比堆顶元素大就交换然后shiftDown
        for(int i = k; i<length; i++) {
            if(nums[i]>nums[0]) {
                Utils.swap(nums, i, 0);
                shiftDown(nums, i, k);
            }
        }
    }



    public void shiftDown(int[] nums, int parentIndex, int endIndex) {
        int temp = nums[parentIndex];
        int childLeft = getChildLeftIndex(parentIndex);
        while(childLeft<=endIndex) {
    //右侧脚标在取值范围内，且右侧小于左侧 则脚标+1
            if(childLeft+1<=endIndex && nums[childLeft+1]<nums[childLeft]){
                childLeft ++;
            }
            if(temp <= nums[childLeft]) {
                break;
            }
            nums[parentIndex] = nums[childLeft];
            parentIndex = childLeft;
            childLeft = getChildLeftIndex(childLeft);
        }
        nums[parentIndex] = temp;
    }

    public int getChildLeftIndex(int parentIndex) {
        return 2*(parentIndex)+1;
    }
}
