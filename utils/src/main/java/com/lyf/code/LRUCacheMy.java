package com.lyf.code;


import java.util.HashMap;
import java.util.Map;

public class LRUCacheMy {
//    class LNode {
//        int key;
//        int data;
//        LNode pre;
//        LNode next;
//
//        LNode() {
//        }
//
//        LNode(int key, int data) {
//            this.key = key;
//            this.data = data;
//        }
//    }
//
//    private int capacity;
//    private Map<int, LNode> linkHashMap;
//
//    private LNode head, tail;
//
//    public LRUCacheMy(int capacity) {
//        linkHashMap = new HashMap<>(capacity);
//        this.capacity = capacity;
//        this.head = new LNode();
//        this.tail = new LNode();
//        this.head.next = this.tail;
//        this.tail.pre = this.head;
//    }
//
//    public int get(int key) {
//        LNode lNode = linkHashMap.get(key);
//        if (null != lNode) {
//            moveToHead(lNode);
//            return lNode.data;
//        }
//        return -1;
//    }
//
//    public void put(int key, int value) {
//        LNode lNode = this.linkHashMap.get(key);
//        boolean exit = null != lNode;
//        if (exit) {
//            lNode.data = value;
////            this.linkHashMap.put(key, lNode);
//            moveToHead(lNode);
//        } else {
//            LNode newNode = new LNode(key, value);
//            this.linkHashMap.put(key, newNode);
//            addToHead(newNode);
//            if (this.linkHashMap.size() > this.capacity) {
//                LNode removeTail = removeTail();
//                this.linkHashMap.remove(removeTail.key);
//            }
//        }
//    }
//
//    private void addToHead(LNode lNode) {
//        lNode.next = this.head.next;
//        this.head.next.pre = lNode;
//        this.head.next = lNode;
//        lNode.pre = this.head;
//    }
//
//    private void removeNode(LNode lNode) {
//        lNode.pre.next = lNode.next;
//        lNode.next.pre = lNode.pre;
//    }
//
//    private void moveToHead(LNode lNode) {
//        removeNode(lNode);
//        addToHead(lNode);
//    }
//
//    private LNode removeTail() {
//        LNode s = tail.pre;
//        removeNode(s);
//        return s;
//    }


    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int capacity;
    private int size;

    class Node {
        private int key;
        private int value;
        private Node pre;
        private Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getPre() {
            return pre;
        }

        public void setPre(Node pre) {
            this.pre = pre;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public LRUCacheMy() {
    }

    public LRUCacheMy(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public void put(int key, int value){
        Node node = map.get(key);
        if (null == node) {
            // 无：
            node = new Node(key, value);
            add2Head(node);
            map.put(key, node);
            this.size++;
            if (size > capacity) {
                Node lastNode = removeTail();
                int lastNodeKey = lastNode.getKey();
                map.remove(lastNodeKey);
                this.size--;
            }
        } else {
            // 有:替换value，移动到最前边
            node.setValue(value);
            move2Head(node);
        }
    }

    public int get(int key){
        Node node = map.get(key);
        if (null == node) {
            return -1;
        } else {
            move2Head(node);
            return node.getValue();
        }
    }

    private void add2Head(Node node) {
        node.next = this.head.next;
        this.head.next.pre = node;
        this.head.next = node;
        node.pre = this.head;
    }

    private void move2Head(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        add2Head(node);
    }

    private Node removeTail() {
        Node node = this.tail.pre;
        node.pre.next = this.tail;
        this.tail.pre = node.pre;
        return node;
    }

}