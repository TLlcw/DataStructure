package com.mh.recursion;
/**
 * 斐波那契数列
 * */
public class FibonacciSequence {
    public static void main(String[] args) {
        System.out.println(fs(7));
        System.out.println(fs(10));
    }
    private static int fs(int n){
        if(n<=2){
            return 1;
        }
        return fs(n-1)+fs(n-2);
    }
}
