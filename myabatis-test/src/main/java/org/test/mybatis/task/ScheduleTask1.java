package org.test.mybatis.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class ScheduleTask1 {

    @Resource(name = "asyncServiceExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    private final Object lock01 = new Object();
    private final Object lock02 = new Object();

    private final ReentrantLock lock1 = new ReentrantLock();
    // 加锁：防止定时任务没有执行完，下一轮其它线程进入
    private final ReentrantLock lock2 = new ReentrantLock();

    @Async("asyncServiceExecutor")
    @Scheduled(fixedDelayString = "1000")
    public void task0() throws InterruptedException {
        synchronized (lock01) {
            System.out.println("task0000000000000000000start:" + Thread.currentThread().getName() + ":"  + new Date());
            Thread.sleep(60000);
            System.out.println("task0000000000000000000end  :" + Thread.currentThread().getName() + ":"  + new Date());
        }

//        if (lock1.tryLock()) {
//            try {
//                System.out.println("task0******************start" + Thread.currentThread().getName() + ":"  + new Date());
//                Thread.sleep(100000);
//                System.out.println("task0******************end:" + Thread.currentThread().getName() + ":"  + new Date());
//            } finally {
//                lock1.unlock();
//            }
//        } else {
//            System.out.println("任务0当前正被执行，跳过此次执行。");
//        }
    }

    @Async("asyncServiceExecutor")
    @Scheduled(fixedDelayString = "1000")
    public void task01() throws InterruptedException {
        synchronized (lock02) {
            System.out.println("task0000000000000000000111111start:" + Thread.currentThread().getName() + ":"  + new Date());
            Thread.sleep(10000);
            System.out.println("task0000000000000000000111111end  :" + Thread.currentThread().getName() + ":"  + new Date());
        }

//        if (lock1.tryLock()) {
//            try {
//                System.out.println("task0******************start" + Thread.currentThread().getName() + ":"  + new Date());
//                Thread.sleep(100000);
//                System.out.println("task0******************end:" + Thread.currentThread().getName() + ":"  + new Date());
//            } finally {
//                lock1.unlock();
//            }
//        } else {
//            System.out.println("任务0当前正被执行，跳过此次执行。");
//        }
    }

    /**
     * fixedDelayString:等到方法执行完成后延迟配置的时间再次执行该方法
     */
    @Async("asyncServiceExecutor")
//    @Scheduled(initialDelayString = "5000" ,fixedDelayString = "10000")
    public void task2lyf() {
        System.out.println(taskExecutor.getThreadPoolExecutor().getPoolSize());
        System.out.println(taskExecutor.getThreadPoolExecutor().getActiveCount());
        if (lock2.tryLock()) {
            try {
                System.out.println("task1111111111111111111start:" + Thread.currentThread().getName() + ":" + new Date());
//            System.out.println(1/0);
                System.out.println("task1111111111111111111end  :" + Thread.currentThread().getName() + ":" + new Date());
            } finally {
                lock2.unlock();
            }

        } else {
            System.out.println("任务1当前正被执行，跳过此次执行。");
        }
    }
}
