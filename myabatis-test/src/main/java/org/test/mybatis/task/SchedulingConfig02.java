//package org.test.mybatis.task;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class SchedulingConfig02 implements CommandLineRunner {
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        //创建实例
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
//        //scheduleAtFixedRate固定多久就触发一次任务
//        //延迟5秒后开始执行任务，任务执行完后延迟10秒执行下一次
//        scheduler.scheduleWithFixedDelay(() -> {
//            //一般任务异常处理放在任务执行过程中
//            try {
//                // 模拟任务异常
//                throw new RuntimeException("异常");
//            } catch (Exception e) {
//                //异常处理
//                System.out.println("异常日志");
//            }
//        }, 5, 10, TimeUnit.SECONDS);
//
//        // 优雅地关闭线程池，等待所有任务完成后再继续执行关闭操作。
//        scheduler.shutdown(); // 或者使用 scheduler.shutdownNow(); 立即关闭（不等待正在执行的任务完成）
//        try {
//            if (!scheduler.awaitTermination(8, TimeUnit.SECONDS)) { // 等待最多8秒，以便让所有任务完成。
//                scheduler.shutdownNow(); // 超时的情况下尝试立即关闭。
//            }
//        } catch (InterruptedException ex) {
//            scheduler.shutdownNow(); // 当前线程被中断时尝试立即关闭。
//            Thread.currentThread().interrupt(); // 重设中断状态，以便调用者能够知道中断已经发生。
//        }
//    }
//}