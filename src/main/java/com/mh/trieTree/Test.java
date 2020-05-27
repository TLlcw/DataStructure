package com.mh.trieTree;

public class Test {
    public static void main(String[] args) {
        TrieTree trieTree=new TrieTree();
        trieTree.insert("word");
        trieTree.insert("wocd");
        trieTree.insert("wored");
        trieTree.insert("worddasfsd");
        trieTree.insert("wordttfsd");
        trieTree.insert("wordsfsd");
        trieTree.insert("wordeesfsd");
        trieTree.insert("worddasfcsasfsd");
        System.out.println("查询单词是否存在");
        System.out.println(trieTree.search("woad"));
        System.out.println(trieTree.search("wordeesfsd"));
        System.out.println("查询以wo开头的所有单词");
        System.out.println(trieTree.prefixMatch("wo"));
        System.out.println("查询以word开头的单词");
        System.out.println(trieTree.prefixMatch("word"));
        System.out.println("查询树中所有的单词");
        System.out.println(trieTree.getAll());
        System.out.println("删除单词wordsfsd");
        trieTree.deleteWord("wordsfsd");
        System.out.println(trieTree.getAll());


    }
}
