package com.mh.cocktailSort;

import java.util.Arrays;
/**
 * @author mh
 * 鸡尾酒排序
 * 从低到高和从高到底两个方向排序
 * 平均时间复杂度为O(n*n)
 * 只有在一开始大部分已经排过序的情况下时间复杂度会接近O(n)
 * */
public class CocktailSort {

    public static int[] cocktailSort(int[] arr)
    {
        int len = arr.length;
        //将最小值排到队尾
        for(int i = 0 ; i < len/2 ; i++)
        {
            for(int j = i ; j < len-i-1 ; j++)
            {
                if(arr[j] < arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            //将最大值排到队头
            for(int j = len-1-(i+1); j > i ; j--)
            {
                if(arr[j] > arr[j-1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
            System.out.println("第"+i+"次排序结果："+ Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr={1,49,13,45,3,4,65,15,4,23,45,3,5,465,3,65,35,5};
        int[] cocktailSort = cocktailSort(arr);
        System.out.println(Arrays.toString(cocktailSort));
    }
}
