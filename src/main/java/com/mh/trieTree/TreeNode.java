package com.mh.trieTree;

import java.util.Arrays;

public class TreeNode {
    public boolean isEnd=false;
    public TreeNode[] next=new TreeNode[26];

    public TreeNode() {
    }



    @Override
    public String toString() {
        return "TreeNode{" +
                "isEnd=" + isEnd +
                ", next=" + Arrays.toString(next) +
                '}';
    }
}
