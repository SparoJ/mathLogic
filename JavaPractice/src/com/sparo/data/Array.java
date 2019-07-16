package com.sparo.data;

/**
 * description: 动态数组 类ArrayList
 * Created by sdh on 2019-07-03
 * 1.添加元素 【扩展 添加头和尾】
 * 2.获取查询和修改元素
 * 3.包含 搜索 和 删除元素
 */
public class Array<E> {
    private E[] data;
    //动态数组当前的有效元素个数
    private int size;

    /**
     * 构造方法 声明创建动态数组
     * @param capacity 初始化容量
     */
    @SuppressWarnings("unchecked")
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
    }

    /**
     * default construct method with initial count of 10
     */
    @SuppressWarnings("unchecked")
    public Array() {
//        data  = (E[]) new Object[10];
        this(10);
    }

    /**
     * size of the dynamic array
     */
    public int getSize() {
        return size;
    }

    /**
     * check whether is empty
     * @return data.length
     */
    public boolean isEmpty() {
        return size==0;
    }

    public int getCapacity() {
        return data.length;
    }

    /**
     * 查找指定位置的数组元素
     * @param index
     * @return
     */
    public E find(int index) {
        if ((index<0 || index>=size)) {
            throw new IllegalArgumentException("the index u find is illegal");
        }
        return data[index];
    }

    /**
     * 修改指定位置的元素
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if ((index<0 || index>=size)) {
            throw new IllegalArgumentException("the index u find is illegal");
        }
        data[index] = e;
    }

    /**
     * 在指定位置添加元素
     * @param index 元素要添加到的角标
     * @param e 要添加的元素
     */
    public void addElement(int index, E e) {
        //index 可以 = size 表示 添加到末尾，通常取元素是无法取到size位置的item的
        if (index<0 || index > size) {
            throw new IllegalArgumentException("the index you want to add is illegal");
        }
        //先找到 index对应的元素 然后让所有index位置之后的所有元素（包括index元素）后移【这里使用倒序循环】
        System.out.println(size + "::capacity::" + getCapacity());
        if(size == getCapacity()) {
            resize(getCapacity()*2);
        }
        for(int i = size-1; i>index-1; i--) { //从最后一个元素到index指向的元素向后移动
            //向后移动即 赋值给后一个元素
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 添加到数组头
     * @param e
     */
    public void addLast(E e) {
        addElement(size, e);
    }

    /**
     * 添加到数组尾
     * @param e
     */
    public void addFirst(E e) {
        addElement(0, e);
    }

    /**
     * 判断数组中是否存在该元素
     * @param e
     * @return yes／no
     */
    public boolean contains(E e) {
        return findElement(e)!=-1;
    }

    /**
     * 查找指定元素在数组中的位置
     * @param e
     * @return
     */
    public int findElement(E e) {
        for (int i = 0; i < size; i++) {
            if(data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定位置的元素
     * @param index
     */
    public E remove(int index) {
        if(index<0 || index>=size) {
            throw new IllegalArgumentException("the index u wanna remove is illegal,please check");
        }
        E removeElement = data[index];
        //删除元素并移动
//        for(int i = index; i<size; i++) { 当i = size-1 然后再去数组中找时，会角标越界 改造方案：i = index+1 data[i-1]
//            data[i] = data[i+1];
//        }
        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }
        size --;
        //节省内存空间
        if(size == getCapacity()/4) {
            resize(getCapacity()/4);
        }
        return removeElement;
    }

    /**
     * @return 返回数组首部元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * @return 返回数组尾部元素
     */
    public E removeLast() {
        return remove(size-1); //注意角标越界问题 集合中最后一个元素 对应 集合大小 size-1， 第一个元素标记为 0位置
    }

    public E getFirst() {
        return data[0];
    }

    /**
     * 删除指定元素
     * @param e
     */
    public void removeElement(E e) {
        // 首先找到对应元素所在的位置
        int position = findElement(e);
        // 其次移除元素并改变数组中删除元素位置之后的元素
        if (position != -1) {
            //合法才删除
            remove(position);
        }
    }

    /**
     * resize 当前数组，完成动态数组能力的基础
     * @param capacity resize的容量
     */
    private void resize(int capacity) {
        //创建新数组
        E[] arr = (E[]) new Object[capacity];
        //遍历复制data数组中的元素到 arr中
        for (int i = 0; i < getSize(); i++) {
            arr[i] = data[i];
        }
        data = arr;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("array size = %d, array capacity = %d\n", size, getCapacity()));
        builder.append("array head {");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i!= size-1) {
                builder.append(", ");
            }
        }
        builder.append("} tail");
        return builder.toString();
    }
}
