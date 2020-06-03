package com.mh.SkipList;

public class Test {
    public static void main(String[] args) {
        SkipList skipList=new SkipList();
        skipList.insert(10);
        skipList.insert(19);
        skipList.insert(5);
        skipList.insert(15);
        skipList.insert(7);
        skipList.insert(27);
        skipList.insert(3);
        skipList.insert(9);
        skipList.printAllNode();
        skipList.delete(9);
        skipList.printAllNode();
    }
}
