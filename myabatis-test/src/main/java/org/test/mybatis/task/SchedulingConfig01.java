package org.test.mybatis.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableScheduling
@EnableAsync
public class SchedulingConfig01 {
    private final int corePoolSize=10;
    private final int maxPoolSize=10;
    private final int queueCapacity=25;
    private final String namePrefix="AsyncTask-";

    /**
     * 自定义线程池配置类。
     * 不要命名为 taskScheduler，与spring框架的bean重名。
     * @return
     */
    @Bean(name = "asyncServiceExecutor")
    public Executor asyncServiceExecutor() {
        //阿里巴巴编程规范：线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。

        //SpringBoot项目，可使用Spring提供的对 ThreadPoolExecutor 封装的线程池 ThreadPoolTaskExecutor：
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        ThreadPoolTaskExecutor executor = new MyThreadPoolTaskExecutor();//自定义ThreadPoolTaskExecutor，会打印线程池情况
        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //配置队列大小
        executor.setQueueCapacity(queueCapacity);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(namePrefix);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        //     1、CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行。
        //        "该策略既不会抛弃任务，也不会抛出异常，而是将任务回推到调用者。"顾名思义，在饱和的情况下，调用者会执行该任务（而不是由多线程执行）
        //     2、AbortPolicy：拒绝策略，直接拒绝抛出异常
        //     3、。。。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}