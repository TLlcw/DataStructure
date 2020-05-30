package com.mh.BinaryHeap;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        HeapSort heapSort=new HeapSort();
        int[] arr={5,8,3,54,1,6,16,46,1,31,64,35,26};

        int[] arrSort = heapSort.heapSort(arr);
        System.out.println(Arrays.toString(arrSort));
    }
}
