package com.mh.SkipList;
/**
 * @author mh
 * */
public class Node {
    public int value=-1;
    public int level;   //跨越几层
    public Node[] next; //指向下一个结点

    public Node(int value, int level) {
        this.value = value;
        this.level = level;
        this.next = new Node[level];
    }
}
