package com.lyf.es.controller;

import com.lyf.es.annotations.IpAddr;
import com.lyf.es.service.EsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private EsService esService;
    @GetMapping("/get")
    public Object esGet() {
        return esService.esGet();
    }

    @SneakyThrows
    @IpAddr
    @GetMapping("/hello")
    public Object hello() {
        System.out.println("hello");
        return "hello";
    }
}
