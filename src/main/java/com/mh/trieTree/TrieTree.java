package com.mh.trieTree;

import java.util.ArrayList;
import java.util.List;
/**
 * @author mh
 * */
public class TrieTree {
    //初始头结点
    private TreeNode startNode=new TreeNode();
    /**
     * 插入字符串
     * */
    public void insert(String msg){
        //从头结点开始遍历树
        TreeNode node=startNode;
        /**
         * 从头结点的子节点开始遍历要插入字符串msg的所有字符
         * 为空则new
         * 知道遍历到msg的最后一个字符
         * */
        for(int i=0;i<msg.length();i++){
            if(node.next[msg.charAt(i)-'a']==null){
                node.next[msg.charAt(i)-'a']=new TreeNode();
            }
            //将当前结点赋值为要遍历的下一结点
            node=node.next[msg.charAt(i)-'a'];
        }
        //最后一个字符所对应的结点，标记isEnd为true，表示已经结束，为一个单词
        node.isEnd=true;
    }
    /**
     * 查询是否存在
     * */
    public boolean search(String msg){
        //从头结点开始遍历树
        TreeNode node=startNode;
        /**
         * 开始遍历msg的每一个字符
         * 如果遍历带不存在的结点则表示改字符串不在树中  返回true
         * */
        for(int i=0;i<msg.length();i++){
            if(node.next[msg.charAt(i)-'a']==null){
                return false;
            }
            //将当前结点至指向要遍历的下一个结点
            node=node.next[msg.charAt(i)-'a'];

        }
        //如果存在则返回当前结点的isEnd  为true
        return node.isEnd;
    }
    /**
     * 获取以prefix为前缀的所有单词
     * */
    public List<String> prefixMatch(String msg){
        //新建list来存放找到以prefix为前缀的单词
        List<String> list=new ArrayList<>();
        //赋值头结点
        TreeNode node=startNode;
        /**
         * 遍历前缀
         * 并找到前缀中最后一个字符所对应的结点
         * 如果又一个字符没找到表示树种没有以prefix为首的单词
         * */
        for(int i=0;i<msg.length();i++){
            if(node.next[msg.charAt(i)-'a']==null){
                return null;
            }
            //指向下一结点
            node=node.next[msg.charAt(i)-'a'];
        }
        //将前缀先写入stringBuilder
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(msg);
        //将前缀和最后一个字符对应的结点传入以结点查找单词的函数中
        //返回所有以prefix为前缀的单词
        List<String> all = getAllOfPrefix(node, stringBuilder, new ArrayList<String>());
        return all;
    }
    /**
     * 获得以某一结点为首的所有的单词
     * @param treeNode 传入结点
     * @param word  单词前缀
     * @param list  用在存放单词
     * */
    public List<String> getAllOfPrefix(TreeNode treeNode,StringBuilder word,List<String> list){
        //如果传递的结点为null 返回
        //结束递归
        if(treeNode==null){
            return null;
        }
        //isEnd为true表示找到一个单词
        //写入list中
        if(treeNode.isEnd==true){
            list.add(word.toString());
        }
        //循环递归所有子节点
        for(int i=0;i<26;i++){
            //递归进入前把递归遍历的字符加入word中以便将找到的单词写入list中
            word.append((char) ('a'+i));
            //递归
            getAllOfPrefix(treeNode.next[i],word,list);
            //递归返回后将遍历完成的字符去掉
            word.deleteCharAt(word.length()-1);
        }
        return list;
    }
    /**
     * 获取trie树中的所有单词
     * */
    public List<String> getAll(){
        //获取前缀为 ""的单词及获得所有单词
        return prefixMatch("");
    }
    /**
     * 删除单词
     * */
    public void deleteWord(String msg){
        //判断单词是否存在
        if(!search(msg)){
            return;
        }
        //存在就删除单词
        deleteWord(startNode,msg,0);

    }
    /**
     * 删除单词
     * */
    public void deleteWord(TreeNode treeNode,String msg,int d){
        //递归长度和要删除的单词长度相等
        //表示以到达单词最有一个字符对应的结点
        //将isEnd赋值为false表示删除单词
        if(d==msg.length()){
            treeNode.isEnd=false;
        }else{
            //递归子节点
            deleteWord(treeNode.next[msg.charAt(d)-'a'],msg,d+1);
        }
        //isEnd为true，表示该结点被其他单词使用
        //不能删除，返回
        //并且该结点以上的结点因为字节点不为null不能删除
        if(treeNode.isEnd){
            return;
        }
        /**
         * 遍历该结点的子节点
         * 不为null表示该结点还有其他单词使用，不能删除结点
         * 直接返回
         * */
        for(int i=0;i<26;i++){
            if(treeNode.next[i]!=null){
                return;
            }
        }
        //表示该节点下以无结点，也并不是结束结点，删除
        treeNode=null;
    }
}
