package com.jta.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

/**
 * @author andyhoop
 * @data 2023/1/10 13:49
 */
@RestController
@RequestMapping("")
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public Optional sendMessage(){
        UUID uuid = UUID.randomUUID();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String message = "发送了一条消息到mq" + uuid + "发送时间为："+ time;
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",message);
        return Optional.of("ok");
    }
}
