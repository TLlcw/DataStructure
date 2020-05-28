package com.mh.AVLTree;
/**
 * @author mh
 * */
public class AVLTree {
    //根结点
    private AvlNode rootNode;
    //计算结点的高度
    public int height(AvlNode avlNode){
        if(avlNode==null){
            return -1;
        }else {
            return avlNode.height;
        }
    }
    //结点的结构为左左型  进行右旋完成平衡
    public AvlNode R_Rotate(AvlNode k2){
        AvlNode k1;
        //获取传入节点的左节点
        k1=k2.lchild;
        //将k1的右节点绑定到k2的左结点上（如果有的话）
        k2.lchild=k1.rchild;
        //将k2结点绑定到k1的右结点上 完成右旋
        k1.rchild=k2;
        //如果k2为根结点，右旋完成后根节点以改变为k1
        if(rootNode==k2){
            rootNode=k1;
        }
        /**
         * 重新计算结点的高度
         * 取左右孩子中最高的结点高度+1为当前结点高度
         * */
        k2.height=Math.max(height(k2.lchild),height(k2.rchild))+1;
        k1.height=Math.max(height(k1.lchild),height(k1.rchild))+1;
        //返回自旋完成后的根结点
        return k1;
    }
    //右右型 进行左旋完成平衡
    public AvlNode L_Rotate(AvlNode k2){
        AvlNode k1;
        //取出传入结点的右结点
        k1=k2.rchild;
        //将k1的左节点绑定到k2的右结点上
        k2.rchild=k1.lchild;
        //将k2结点绑定到k1的左节点上
        k1.lchild=k2;
        //如果k2为根结点，右旋完成后根节点以改变为k1
        if(rootNode==k2){
            rootNode=k1;
        }
        /**
         * 重新计算结点的高度
         * 取左右孩子中最高的结点高度+1为当前结点高度
         * */
        k2.height=Math.max(height(k2.lchild),height(k2.rchild))+1;
        k1.height=Math.max(height(k1.lchild),height(k1.rchild))+1;
        //返回自旋完成后的根结点
        return k1;
    }
    //左右型  先左旋 在右旋
    public AvlNode L_R_Rotate(AvlNode k3){

        /**
         * 将k3的左结点完成左旋
         * 将自旋完成后的结点重新赋值到k3的左节点
         * 结点类型变为左左型
         * */
        k3.lchild=L_Rotate(k3.lchild);
        //左左型结点进行右旋
        return R_Rotate(k3);
    }
    //右左型  先右旋  在左旋
    public AvlNode R_L_Rotate(AvlNode k3){
        /**
         * 将k3的右结点完成左旋
         * 将自旋完成后的结点重新赋值到k3的右节点
         * 结点类型变为右右型
         * */
        k3.rchild=R_Rotate(k3.rchild);
        //右右型结点进行左旋
        return L_Rotate(k3);
    }
    /**
     * 插入结点
     * @param data 插入的数据
     * @param avlNode  当前遍历的结点  用于递归
     * */
    private AvlNode insert(int data,AvlNode avlNode){
        /**
         * 当前遍历的结点为null，表示以遍历到叶子节点处
         * 新建结点并表示已经绑定到该节点的子节点上
         * */
        if(avlNode==null){
            avlNode=new AvlNode();
            avlNode.data=data;
            avlNode.rchild= avlNode.lchild=null;
        }
        /**
         * 要插入的数据比当前结点的数据小
         * 递归遍历改结点的左节点
         * */
        if(data<avlNode.data){
            avlNode.lchild = insert(data, avlNode.lchild);
            //判断左右结点的高度 ，高度相差二则进行自旋
            if(height(avlNode.lchild)-height(avlNode.rchild)==2){
                //要插入数据小于左节点数据， 结点类型为左左型
                if(data<avlNode.lchild.data){
                    //进行右旋  并返回自旋完的结点
                    avlNode=R_Rotate(avlNode);
                }else{
                    //否则为左右型  进行左右旋
                    avlNode=L_R_Rotate(avlNode);
                }
            }
        }
        /**
         * 要插入的数据比当前结点的数据大
         * 递归遍历改结点的右节点
         * */
        if(data>avlNode.data){
            avlNode.rchild=insert(data,avlNode.rchild);
            //判断左右结点的高度 ，高度相差二则进行自旋
            if(height(avlNode.rchild)-height(avlNode.lchild)==2){
                //要插入数据大于左节点数据， 结点类型为右右型
                if(data>avlNode.rchild.data){
                    //进行左旋  并返回自旋完的结点
                    avlNode=L_Rotate(avlNode);
                }else{
                    //否则为右左型  进行右左旋
                   avlNode= R_L_Rotate(avlNode);
                }
            }
        }
        //插入结点完成后重新计算结点的高度
        avlNode.height=Math.max(height(avlNode.rchild),height(avlNode.lchild))+1;
        //返回
        return avlNode;
    }
    /**
     * 插入数据
     * */
    public void insert(int data){
        //如果根结点为空 ，将要插入的数据赋值到根结点
        if(rootNode==null){
            rootNode=new AvlNode();
            rootNode.data=data;
        }
        //插入
        insert(data,rootNode);
    }
}
