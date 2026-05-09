package com.lyf.poi.schedulTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TaskTest {
    private final static Logger logger = LoggerFactory.getLogger(TaskTest.class);

//    @Scheduled(initialDelayString = "0", fixedRateString = "10800000")
    private void test() {
        for (int i = 0; i < 100; i++) {
            logger.trace("{}======trace", i);
            logger.debug("{}======debug", i);
            logger.info("{}======info", i);
            logger.warn("{}======warn", i);
            logger.error("{}======error", i);
        }
    }
}
