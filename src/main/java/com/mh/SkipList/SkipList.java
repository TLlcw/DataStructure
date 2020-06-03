package com.mh.SkipList;
/**
 * @author mh
 * */
//跳跃表
public class SkipList {
    //允许的最大层数
    private int maxLevel = 16;
    //头节点，充当辅助。
    private Node head = new Node(-1, 16);
        //当前跳跃表节点的个数
        private int size = 0;
        //当前跳跃表的层数,初始化为1层。
        private int levelCount = 1;
        /**
         *查找结点
         * */
        public Node find(int value) {
            //从头结点开始找
            Node temp = head;
            /**
             * 根据当前跳跃表的最高层数开始查找
             * 根据下一个结点是否比要查找的结点大判断是否要进入下一层
             * 知道下沉到第一层找到该节点的前一结点（如果存在的话）
             * */
            for (int i = levelCount - 1; i >= 0; i--) {
                while (temp.next[i] != null && temp.next[i].value < value) {
                    temp = temp.next[i];
                }
            }
            //判断是否有该元素存在
            if (temp.next[0] != null && temp.next[0].value == value) {
                System.out.println(value + "  查找成功");
                return temp.next[0];
            } else {
                //不存在返回null
                return null;
            }
        }


        /**
         * 插入数据
         * */
        public void insert(int value){
            //首先根据掷筛子决定插入结点的层数
            int level=getLevel();
            //实例化该结点
            Node newNode = new Node(value,level);
            /**
             * 根据结点的层数new一个数组，用来记录插入该结点的前驱
             * 就是在插入结点时所有拐弯的结点，
             * 因为需要把从最底层到随机到的层数所有层都插入不同层的链表中
             * */
            Node[] update=new Node[level];
            //从根节点开始查找
            Node temp = head;
            //从随机到的层数开始寻找要插入的位置
            for (int i = level - 1; i >= 0; i--) {
                while (temp.next[i] != null && temp.next[i].value < value) {
                    temp = temp.next[i];
                }
                //把拐弯的结点赋值到数组内
                update[i] = temp;
            }

            //把插入节点的每一层连接起来
            //新节点的每一层的前驱后后继都存在update结点的属性中
        for (int i = 0; i < level; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
        System.out.println(value + " 插入成功");
        //跳跃表结点个数加一
        size++;
        //判断是否需要更新跳跃表的层数
        if (level > levelCount) {
            levelCount = level;
        }
    }
    /**
     * 删除数据
     * */
    public void delete(int value) {
        //新建一个结点数组来保存要删除元素每层的前驱和后继
        //方便链表删除结点
        Node[] update = new Node[levelCount];
        Node temp = head;
        //开始寻找删除结点的位置并把每层的前驱后后继保存到update中
        for (int i = levelCount - 1; i >= 0; i--) {
            while (temp.next[i] != null && temp.next[i].value < value) {
                temp = temp.next[i];
            }
            update[i] = temp;
        }
        //开始删除操作，通过每层的赋值悬空要删除的结点（链表结点的删除）
        if (temp.next[0] != null && temp.next[0].value == value) {
            for (int i = levelCount - 1; i >= 0; i--) {
                if (update[i].next[i] != null && update[i].next[i].value == value) {
                    update[i].next[i] = update[i].next[i].next[i];
                }
            }
            size--;
            System.out.println(value + " 删除成功");
        }
    }
    //打印所有节点
     public void printAllNode() {
        Node temp = head;
        //遍历最低层打印所有结点
        while (temp.next[0] != null) {
            System.out.println(temp.next[0].value + "  ");
            temp = temp.next[0];
        }
    }

    /**
     * 模拟抛硬币
     * 跳跃表中新插入结点的层数时根据抛硬币来决定的
     * 抛到正面加一层继续抛，抛到反面终止返回层数
     * */
    private int getLevel() {
        int level = 1;
        while (true) {
            int t = (int)(Math.random() * 100);
            if (t % 2 == 0) {
                level++;
            } else {
                break;
            }
        }
        return level;
    }
}
