package com.jta.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    List<String> fileCache = new ArrayList<>();


    public void testList() throws InterruptedException {
        Thread.sleep(1000);
        fileCache = new ArrayList<>();
        fileCache.add(System.currentTimeMillis() + "");
        for (String s : fileCache) {
            System.out.println(s);
        }
    }
}
