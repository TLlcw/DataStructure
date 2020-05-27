package com.mh.trieTree;

import java.util.ArrayList;
import java.util.List;

public class TrieTree {
    //初始结点
    private TreeNode startNode=new TreeNode();
    /**
     * 插入字符串
     * */
    public void insert(String msg){
        TreeNode node=startNode;
        for(int i=0;i<msg.length();i++){
            if(node.next[msg.charAt(i)-'a']==null){
                node.next[msg.charAt(i)-'a']=new TreeNode();
            }
            node=node.next[msg.charAt(i)-'a'];
        }
        node.isEnd=true;
    }
    /**
     * 查询是否存在
     * */
    public boolean search(String msg){
        TreeNode node=startNode;
        for(int i=0;i<msg.length();i++){
            if(node.next[msg.charAt(i)-'a']==null){
                return false;
            }
            node=node.next[msg.charAt(i)-'a'];

        }
        return node.isEnd;
    }
    /**
     * 获取以prefix为前缀的所有单词
     * */
    public List<String> prefixMatch(String msg){
        List<String> list=new ArrayList<>();
        TreeNode node=startNode;
        for(int i=0;i<msg.length();i++){
            if(node.next[msg.charAt(i)-'a']==null){
                return null;
            }
            node=node.next[msg.charAt(i)-'a'];
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(msg);
        List<String> all = getAllOfPrefix(node, stringBuilder, new ArrayList<String>());
        return all;
    }
    /**
     * 获得以某一结点为首的所有的单词
     * */
    public List<String> getAllOfPrefix(TreeNode treeNode,StringBuilder word,List<String> list){
        if(treeNode==null){
            return null;
        }
        if(treeNode.isEnd==true){
            list.add(word.toString());
        }
        for(int i=0;i<26;i++){
            word.append((char) ('a'+i));
            getAllOfPrefix(treeNode.next[i],word,list);
            word.deleteCharAt(word.length()-1);
        }
        return list;
    }
    /**
     * 获取trie树中的所有单词
     * */
    public List<String> getAll(){
        return prefixMatch("");
    }
    /**
     * 删除单词
     * */
    public void deleteWord(String msg){
        if(!search(msg)){
            return;
        }
        deleteWord(startNode,msg,0);

    }
    /**
     * 删除单词
     * */
    public void deleteWord(TreeNode treeNode,String msg,int d){
        if(d==msg.length()){
            treeNode.isEnd=false;
        }else{
            deleteWord(treeNode.next[msg.charAt(d)-'a'],msg,d+1);
        }
        if(treeNode.isEnd){
            return;
        }
        for(int i=0;i<26;i++){
            if(treeNode.next[i]!=null){
                return;
            }
        }
        treeNode=null;
    }
}
