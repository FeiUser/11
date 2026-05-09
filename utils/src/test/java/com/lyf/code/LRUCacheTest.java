package com.lyf.code;

import org.junit.Test;

/**
 * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 */
public class LRUCacheTest {
    @Test
    public void LRUCacheMyTest(){

        LRUCacheMy cacheMy = new LRUCacheMy( 2 /* 缓存容量 */ );

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

    @Test
    public void LRUCacheMyTest2(){
        Integer i = null;
        boolean b = -1 == i;
        System.out.println(b);
    }


}