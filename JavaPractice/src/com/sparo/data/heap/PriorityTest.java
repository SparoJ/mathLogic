package com.sparo.data.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * description:
 * 通过优先队列了解什么是队列
 * 通过优先队列了解不同底层数据结构构建的队列复杂度关系
 *
 * Created by sdh on 2020-02-05
 */
public class PriorityTest {

    public static void main(String[] args) {
//        int[] arr = {1,1,1,2,2,3};
        int[] arr = {4,1,-1,2,-1,2,3};
        /**
         * [4,1,-1,2,-1,2,3]
         * -1
         * 2
         * 3
         * 4
         * 1
         */

        float test = 1.5f;
        int a = (int) Math.ceil(test);
        System.out.println("a->ceil->" + a);

        PriorityTest pt = new PriorityTest();
        List<Integer> list = pt.topKFrequent(arr, 2);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public class Item implements Comparable<Item>{
        public int num;
        public int frequency;

        public Item(int num, int frequency) {
            this.num = num;
            this.frequency = frequency;
        }
        // Java自带的PriorityQueue 内建的是 最小堆 so 排序入堆时按照正常的顺序add即可，因为当前需要建立保持频率的最小堆
        public int compareTo(Item another) {
            if(this.frequency > another.frequency) {
                return 1; //-1;
            } else if(this.frequency < another.frequency) {
                return -1; //1
            } else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        //整理出数组中每个元素出现的频率
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int length = nums.length;
        for(int i = 0; i < length; i++) {
            if(tm.containsKey(nums[i])) { // containsKey / containsValue
                tm.put(nums[i], tm.get(nums[i])+1);
            } else {
                tm.put(nums[i], 1);
            }
        }
        System.out.println(tm);
        PriorityQueue<Item> pq = new PriorityQueue<>();
        //遍历 TreeMap
        for(int key : tm.keySet()) {
            if(pq.size()<k) {
                // java queue 没有 enqueue 和 dequeue
                pq.add(new Item(key, tm.get(key)));
            } else if(tm.get(key)>pq.peek().frequency) {
                //取出最小堆的元素
                pq.poll();
                pq.add(new Item(key, tm.get(key)));
            }
        }
        ArrayList<Integer> ll = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            ll.add(pq.poll().num);
        }
        return ll;
    }
}
