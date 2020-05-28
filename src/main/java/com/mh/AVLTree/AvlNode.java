package com.mh.AVLTree;
/**
 * @author mh
 * */
public class AvlNode {
    //结点存储的数据
    public int data;
    //结点的左孩子
    public AvlNode lchild;
    //右孩子
    public AvlNode rchild;
    //结点的高度
    public int height;
    @Override
    public String toString() {
        return "AvlNode{" +
                "data=" + data +
                ", lchild=" + lchild +
                ", rchild=" + rchild +
                ", height=" + height +
                '}';
    }
}
