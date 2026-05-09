package com.lyf.utils.httpClient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "tyy",url = "http://192.168.12.139:9010")
public interface OrderClientService {

    @PostMapping("/login/userlogin")
    String createOrder();
}
