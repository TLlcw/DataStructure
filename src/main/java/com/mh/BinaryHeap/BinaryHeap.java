package com.mh.BinaryHeap;
/**
 * @author mh
 * */
//最小二叉堆
public class BinaryHeap {
    /**
     * 上浮操作 堆插入的结点进行上浮
     * @param arr 传入的数组
     *
     * 插入的结点在数组的末尾，对数组最后一个元素进行上浮
     * */
    public int[] upAdjust(int[] arr){
        //子节点下标
        int child=arr.length-1;
        //将二叉树存入数组中，其父结点为  （子节点下标-1）/2
        int patent=(child-1)/2;
        //将要上浮的数据放入临时变量里
        int temp=arr[child];
        /**
         * 开始进行上浮操作
         * 如果子节点下标大于0，并且要上浮的数据小于其父结点
         * 交换子节点的父结点
         * */
        while(child>0&&temp<arr[patent]){
            //先将父结点赋值到子节点处
            arr[child]=arr[patent];
            //子节点下标转移到父结点处
            child=patent;
            //找到当前子节点的父结点下标，继续循环判断 是否满足条件
            patent=(child-1)/2;
        }
        //上浮结束，找到数据要插入的节点处，将数据赋值到该节点
        arr[child]=temp;
        //返回数组
        return arr;
    }
    /**
     * 下沉操作，执行删除相当于删除根节点，
     * 把最后一个结点赋值到根节点，在对根节点进行下沉
     * @param arr 传入的数组
     * @param parent 要进行下沉的元素的下标
     * @param length 要处理的组数的长度(不是传入的数组长度)
     * */
    public int[] downAdjust(int[] arr,int parent,int length){
        //将要进行下沉的数据放到临时变量中
        int temp=arr[parent];
        //定位左孩子
        int child=2*parent+1;
        //如果左孩子在要处理的数组中
        while(child<length){
            //判断右孩子是否在要处理的数组中并且比左孩子小，选择孩子中比较小的进行下沉
            if(child+1<length&&arr[child]>arr[child+1]){
                //定位右孩子
                child++;
            }
            //如果子节点大于要下沉的数据，下沉结束，跳出循环
            if(temp<=arr[child]){
                break;
            }
            //进行下沉操作，先将孩子结点赋值到父结点中
            arr[parent]=arr[child];
            //父结点下标转移带子节点处
            parent=child;
            //找到当前父结点的子节点 继续进行循环
            child=child*2+1;
        }
        //循环结束，找到了下沉到的位置
        //将数据保存到该结点中
        arr[parent]=temp;
        //返回数组
        return arr;
    }
    /**
     * 构建二叉堆
     * 构建二叉堆需要从最后一个非叶子处进行下沉操作，直到根节点
     * */
    public int[] buildHead(int[] arr){
        //从最后一个非叶子结点开始下沉
        for(int i=(arr.length-2)/2;i>=0;i--){
            arr=downAdjust(arr,i,arr.length);
        }
        //返回构建好的二叉堆
        return arr;
    }
}
