package com.mh.BinaryHeap;

public class HeapSort {
    //new 二叉堆
    BinaryHeap binaryHeap=new BinaryHeap();
    /**
     *对数组进行堆排序
     * */
    public int[] heapSort(int[] arr){
        //先将数组构建为二叉堆
        int[] binaryArr = binaryHeap.buildHead(arr);
        /**
         * 堆排序 就是将二叉堆的根节点拿到放到数组最后，
         * 将二叉堆的最后一个元素放到根结点处进行下沉
         * 循环拿到最小值完成排序
         * */
        for(int i=binaryArr.length-1;i>=1;i--){
            //将根节点赋值到临时变量中
            int temp=binaryArr[0];
            //将二叉堆中要处理的最后一个元素赋值到根节点处
            binaryArr[0]=binaryArr[i];
            //将取出的根节点在数组后面拍好
            binaryArr[i]=temp;
            //根节点变化后 进行下沉
            binaryHeap.downAdjust(binaryArr,0,i);
        }
        //返回排好序的数组
        return binaryArr;
    }
}
