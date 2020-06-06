package com.mh.SimpleHashMap;
/**
 * @author mh
 * */
public class SimpleHashMap<K,V> {
    //默认的负载因子
    private static float DEFAULT_LOAD_FACTOR =0.75f;
    //默认长度
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;
    //最大长度
    private static final int MAXIMUM_CAPACITY = 1 << 10;
    //当前容量
    private int Capacity;
    //当前长度
    private int size;
    //结点数组
    private Entry[] entries;

    public SimpleHashMap() {
        //不指定的话直接给容量赋默认初值
        this.Capacity=DEFAULT_INITIAL_CAPACITY;
        //初始化函数
        init();
    }
    //自定义容量
    public SimpleHashMap(int capacity) {
        //参数不合理
        if(capacity<=0){
            throw new IllegalArgumentException("初始容量必须大于零");
        }
        //参数超过最大值，容量赋值最大值
        if(capacity>=MAXIMUM_CAPACITY){
            this.Capacity=MAXIMUM_CAPACITY;
        }else{
            /**
             * 根据参数的大小决定最合适的容量
             * 容量必须为2的次方
             * */
            int i=1;
            while(i<capacity){
                i<<=1;
            }
            this.Capacity=i;
        }
        //初始化函数
        init();

    }
    //初始化函数
    private void init(){
        size=0;
        entries=new Entry[Capacity];
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }
    /**
     * 增加元素的方法
     * */
    public void put(K key,V value){
        //通过hashcode与容量-1取与获得数值的下标直接定位到对应的hash槽
        Entry entry=this.entries[(key.hashCode()&(Capacity-1))];
        /**
         * 如果当前槽为空，直接将新节点赋值到该hash槽
         * 如果不为空，则从当前槽的首结点开始遍历
         * 如果与某节点的hash值相同，则说明两个结点的key相同，
         * 将新的value赋值到之前的结点中
         * 否则遍历到链表的末尾，将该结点连接到末尾处
         * */
        if(entry!=null){
        do{
            //hash值相同，更新value
            if(entry.equals(key.hashCode())){
                entry.setValue(value);
                return;
            }
        }while(entry.getNext()!=null&&(entry=entry.getNext())!=null);
        Entry putentry=new Entry(key.hashCode(),key,value,null);
        //将结点连接带链表末尾
        entry.setNext(putentry);
        }else{
            //hash槽为空直接插入槽内
            this.entries[(key.hashCode()&(Capacity-1))]=new Entry(key.hashCode(),key,value,null);
        }
        //每次新增都增加当前长度
        size++;
        //判断是否需要扩容
        if(isResize()){
            //扩容
            resize();
        }
    }
    /**
     * 获取value的方法
     * */
    public V get(K key){
        //通过hash值定位的对应的hash槽
        Entry entry=this.entries[(key.hashCode()&(Capacity-1))];
        //hash槽位空及要查早的元素不存在
        if(entry==null){
            return null;
        }else{
            //从该hash槽对应的链表的首结点开始寻找，比较hashcode
            do{
                if(entry.getHash()==key.hashCode()){
                    return (V) entry.getValue();
                }
                entry=entry.getNext();
            }while (entry!=null);
            return null;
        }
    }
    /**
     * 移除某结点的方法
     * */
    public void remove(K key){
        //通过hash值定位的对应的hash槽
        Entry entry=this.entries[key.hashCode()&(Capacity-1)];
        if(entry==null){
            throw new IllegalArgumentException("要删除的元素容器中不存在");
        }else{

                /**
                 * 判断链表头节点是否为要删除的结点
                 * 如果头节点右下一结点，将下一结点复制到hash槽成为首节点
                 * 否则将该hash槽置为空
                 * */
                if(entry.getHash()==key.hashCode()){
                    if(entry.getNext()!=null){
                        this.entries[key.hashCode()&(Capacity-1)]=entry.getNext();
                        //删除结点，当前长度减一
                        size--;
                        return;
                    }else{
                        this.entries[key.hashCode()&(Capacity-1)]=null;
                    }
                }
        }
        do{
            //通过比较hashcode 从链表中寻找对应的结点
            if (entry.getNext()!=null&&entry.getNext().getHash()==key.hashCode()) {
                if(entry.getNext().getNext()!=null){
                    entry.setNext(entry.getNext().getNext());
                }else{
                    entry.setNext(null);
                }
                size--;
                return;
            }
            entry=entry.getNext();
        }while(entry!=null&&entry.getNext()!=null);
        throw new IllegalArgumentException("要删除的元素容器中不存在");
    }

    /**
     * 判断是否需要扩容
     * */
    public boolean isResize(){
        //判断是否到达默认负载因子
        if(((float)size/(float)Capacity)>=DEFAULT_LOAD_FACTOR){
            return true;
        }
        return false;
    }
    /**
     * 扩容机制
     * */
    public void resize(){
        int orgCap=this.Capacity;
        this.size=0;
        this.Capacity=this.Capacity<<1;
        Entry[] oldEntrys=this.entries;
        this.entries=new Entry[Capacity];
        //将旧结点全部重新插入到新结点数组中
        for(int i=0;i<orgCap;i++){
            Entry entry=oldEntrys[i];
            while(entry!=null){
                this.put((K)entry.getKey(),(V)entry.getValue());
                entry=entry.getNext();
            }
        }
    }
}
