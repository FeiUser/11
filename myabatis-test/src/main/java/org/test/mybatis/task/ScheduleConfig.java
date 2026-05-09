//package org.test.mybatis.task;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//
//import java.util.Date;
//
//@Configuration
//@EnableScheduling
//public class ScheduleConfig implements SchedulingConfigurer {
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(3);  // 设置线程池大小与任务数匹配
//        scheduler.setThreadNamePrefix("ScheduledTask-lyf-");
//        scheduler.initialize();
//        taskRegistrar.setTaskScheduler(scheduler);
//    }
//
//
//    @Scheduled(fixedDelayString = "1000")
//    public void task0() throws InterruptedException {
//        System.out.println("task0000000000000000000start:" + Thread.currentThread().getName() + ":" + new Date());
//        Thread.sleep(60000);
//        System.out.println("task0000000000000000000end  :" + Thread.currentThread().getName() + ":" + new Date());
//
//    }
//
//    /**
//     * fixedDelayString:等到方法执行完成后延迟配置的时间再次执行该方法
//     */
//    @Scheduled(initialDelayString = "5000", fixedDelayString = "10000")
//    public void task2lyf() {
//
//        System.out.println("task1111111111111111111start:" + Thread.currentThread().getName() + ":" + new Date());
////        System.out.println(1 / 0);
//        System.out.println("task1111111111111111111end  :" + Thread.currentThread().getName() + ":" + new Date());
//    }
//}
