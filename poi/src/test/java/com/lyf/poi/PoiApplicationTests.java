package com.lyf.poi;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PoiApplicationTests {
    private static Logger log = LoggerFactory.getLogger(PoiApplicationTests.class);
    @Test
    void contextLoads() {
        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");
    }

}
