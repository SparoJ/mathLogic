package com.sparo.leetcode;

import java.util.HashMap;

/**
 * description: 146. LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *  
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *  
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2  // 缓存容量  );
 *
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // 返回  1
        *cache.put(3,3);    // 该操作会使得关键字 2 作废
        *cache.get(2);       // 返回 -1 (未找到)
        *cache.put(4,4);    // 该操作会使得关键字 1 作废
        *cache.get(1);       // 返回 -1 (未找到)
        *cache.get(3);       // 返回  3
        *cache.get(4);       // 返回  4
        *
        *来源：力扣（LeetCode）
        *链接：https://leetcode-cn.com/problems/lru-cache
        *著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by sdh on 2020-07-17
 */
class LRUCache {

    /**
     * 考察点内容分析：lru通过什么实现的 LinkedHashMap， LinkedHashMap 和HashMap 什么关系，和 LinkedList什么我关系，怎样通过现有的HashMap 和 自建DoubleLinkedList 完成类似 LinkedHashMap 的lru 特性操作
     * lru的 特性在 put（更新元素 ）和 get（存在元素）时体现，在get（元素不存时）返回 -1， 在 put(元素不存在，新建时) 创建新链表节点
     * 另外维护 node和 链表关系的同时也需要维护 node和 hashMap的关系，什么时候维护，对双向链表执行添加元素和删除元素操作时，同样的要对map做相同操作
     * 问题盘点：
     * 1.变量声明使用，即作用
     * 2.如何维护双向链表，使用虚拟头节点来得到 first 和 last 节点方便添加和删除对应位置节点
     * 3.方法复用抽取以及变量含义，在方法调用过程中要明确变量的含义，否则容易传递错误的变量导致问题
     * 4.添加新节点到双向链表时，要关注size 和 capacity大小关系，注意维护，什么时候维护，怎么维护 size++ 和 size-- 要匹配逻辑
     */
    //用于构建双向链表
    class DoubleLinkedNode {
        //记录数据的 key/value
        public int key;
        public int value;
        //记录前/后 链接节点
        public DoubleLinkedNode pre;
        public DoubleLinkedNode next;
        //构造方法
        public DoubleLinkedNode() {}
        //用于构建传值
        public DoubleLinkedNode(int _key, int _value) {
            this.key = _key;
            this.value = _value;
        }
    }

    public DoubleLinkedNode head;
    public DoubleLinkedNode tail;
    //node 个数 用于判定当前双向链表size 和 capacity 关系，超过则维护链表大小移除最少使用的节点
    int size = 0;
    int capacity = 0;
    //创建全局的HashMap 用于维护 key和 节点的映射
    HashMap<Integer, DoubleLinkedNode> map = new HashMap<>();
    public LRUCache(int capacity) {
        size = 0;
        //capacity 容量用于移除最近未使用的元素
        this.capacity = capacity;
        //初始化双向链表的头和尾节点 方便维护双向链表的头尾节点的添加操作等，类似dummyHead 的作用
        head = new DoubleLinkedNode();
        tail = new DoubleLinkedNode();
        //维护双向链表之间节点的关系
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        //如果map中不存在key对应的node 说明不存在key对应的value 返回-1
        DoubleLinkedNode node = map.get(key);
        if(node == null) return -1;
        //获取key对应node的数据后需要维护node 在双向链表中的位置来匹配LRU的特性，最近使用的这里采用放置到双向链表的头节点位置
        //lru特性维护
        accessPropertyKeep(node);
        return node.value;
    }

    private void accessPropertyKeep(DoubleLinkedNode node) {
        //1.维护前/后 节点的关系，将当前node 从双向链表中移除
        removeNode(node);
        //2.将当前node 节点添加到双向链表的头节点处
        addFirstNode(node);
    }

    private void removeNode(DoubleLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        //不需要额外前后指针指向null 因为不会造成链表成环
    }

    private void addFirstNode(DoubleLinkedNode node) {
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
        node.pre = head;
        // node.pre = head;
        // node.next = head.next;
        // head.next.pre = node;
        // head.next = node;

    }

    //移除last node 同removeNode 逻辑一致
    private DoubleLinkedNode removeLastNode() {
        DoubleLinkedNode last = tail.pre; //非node.next
        removeNode(last);
        return last;
    }

    //❌ 未关注size 超过 capactiy的情况？何时处理？ 添加时处理，准确位置是？判定标准是？
    public void put(int key, int value) {
        //判定当前是否有key对应匹配的node 存在于map ，存在则说明需要更新 否则否则直接创建新节点 两种场景都需要维护当前节点和双向链表的关系
        DoubleLinkedNode node = map.get(key);
        if(node == null) {
            node = new DoubleLinkedNode(key, value);
            //添加到双向链表头维护
            addFirstNode(node);
            //未添加到 map中
            map.put(key, node);
            //以下未容易遗漏且出错的点：
            //🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩🚩
            size++;
            if(size>capacity) {
                //删除tail前的节点last
                DoubleLinkedNode last = removeLastNode();
                //添加和删除节点存在两处需要维护的地方，1是map 2是双向链表
                map.remove(last.key);
                size--;
            }
        } else {
            //更新当前节点的值
            node.value = value;
            //lru特性维护，同get 元素存在时的逻辑
            accessPropertyKeep(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
