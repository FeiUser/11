package com.lyf.code;

import java.util.HashMap;
import java.util.Map;

public class LRUCache2 {
    class LNode {
        int data;
        LNode next;
        LNode pre;

        public LNode() {
        }

        public LNode(int data) {
            this.data = data;
        }

    }

    private int maxSize;
    public LNode headNode;
    public LNode tailNode;
    public Map<Integer, LNode> map;

    public LRUCache2(int maxSize) {
        this.maxSize = maxSize;
        this.headNode = new LNode();
        this.tailNode = new LNode();
        this.map = new HashMap<>();
        this.headNode.next = this.tailNode;
        this.tailNode.pre = this.headNode;
    }
    // 头插
    private void headInsert(LNode lNode){
        lNode.next = headNode.next;
        headNode.next.pre = lNode;
        lNode.pre = headNode;
        headNode.next = lNode;
    }

    private void move2head(LNode lNode){
        lNode.pre.next = lNode.next;
        lNode.next.pre = lNode.pre;
        headInsert(lNode);
    }

    private void deleteNode(LNode lNode){
        lNode.next.pre = lNode.pre;
        lNode.pre.next = lNode.next;
    }

    private Integer deleteTail(){
        LNode middle = tailNode.pre;
        deleteNode(middle);
        return middle.data;
    }

    public void put(int key, int value) {
        LNode lNode = map.get(key);
        if (null == lNode) {
            LNode lNode1 = new LNode(value);
            headInsert(lNode1);
            map.put(key, lNode1);
            if (map.size() > maxSize) {
                Integer data = deleteTail();
                map.remove(data);
            }
        } else {
            lNode.data = value;
            move2head(lNode);
        }
    }

    public Integer get(int key) {
        LNode lNode = map.get(key);
        if (null == lNode) {
            return -1;
        } else {
            move2head(lNode);
            return lNode.data;
        }
    }

    public static void main(String[] args) {
        LRUCache2 cacheMy = new LRUCache2(2);
        cacheMy.put(1, 1);                        //                  [1]
        cacheMy.put(2, 2);                        //                  [2,1]
        System.out.println(cacheMy.get(1));       // 返回  1          [1,2]
        cacheMy.put(3, 3);    // 该操作会使得密钥 2 作废                 [3,1]
        System.out.println(cacheMy.get(2));       // 返回 -1 (未找到)  [3,1]
        cacheMy.put(4, 4);    // 该操作会使得密钥 1 作废                 [4,3]
        System.out.println(cacheMy.get(1));       // 返回 -1 (未找到)  [4,3]
        System.out.println(cacheMy.get(3));       // 返回  3           [3,4]
        System.out.println(cacheMy.get(4));       // 返回  4           [4,3]
    }

}