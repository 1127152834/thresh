package com.zhang.thresh.server.system.configure;

import com.zhang.thresh.common.core.entity.constant.ThreshConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 注册线程池
 * @author MrZhang
 */
@Configuration
public class ThreshWebConfigure {

    /**
     * 注册异步线程池 主要用于处理日志操作
     */
    @Bean(ThreshConstant.ASYNC_POOL)
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /**
         * 设置CPU核数
         */
        executor.setCorePoolSize(5);
        /**
         * 设置最大线程数
         */
        executor.setMaxPoolSize(20);
        /**
         * 设置线程池所使用的缓冲队列
         */
        executor.setQueueCapacity(100);
        /**
         * 设置线程池维护线程所允许的空闲时间
         */
        executor.setKeepAliveSeconds(30);
        /**
         * 设置线程池名称
         */
        executor.setThreadNamePrefix("Thresh-Async-Thread");
        /**
         * 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
         */
        executor.setWaitForTasksToCompleteOnShutdown(true);
        /**
         * 设置线程池中任务的等待时间，如果超过这个时间还没有销毁就强制销毁
         */
        executor.setAwaitTerminationSeconds(60);
        /**
         * 设置线程池的拒绝策略为"用调用者所在的线程来执行任务"
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
