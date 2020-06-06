package com.mh.SimpleHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * 测试类
 * */
public class test {
    public static void main(String[] args) {
        SimpleHashMap<String,user> simpleHashMap=new SimpleHashMap<>();
        List<String> list=new ArrayList<>();
        /*simpleHashMap.put("123","456");
        System.out.println(simpleHashMap.get("123"));
        simpleHashMap.put("123","789");
        System.out.println(simpleHashMap.get("123"));*/
        for(int i=0;i<16;i++){
            user u=new user(i,"xiao"+i);
            String str=UUID.randomUUID().toString();
            list.add(str);
            simpleHashMap.put(str,u);
        }
        for(int i=0;i<16;i++){
            System.out.println(simpleHashMap.get(list.get(i)));
        }
        simpleHashMap.remove(list.get(3));
        simpleHashMap.remove(list.get(7));
        for(int i=0;i<16;i++){
            System.out.println(simpleHashMap.get(list.get(i)));
        }
    }
}

class user{
    private int id;
    private String name;

    public user() {
    }

    public user(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}