package com.mh.trieTree;

import java.util.Arrays;
/**
 * @author mh
 * */
public class TreeNode {
    //标记单词是否结束
    public boolean isEnd=false;
    //26个子节点 代表 'a'-'z'
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
