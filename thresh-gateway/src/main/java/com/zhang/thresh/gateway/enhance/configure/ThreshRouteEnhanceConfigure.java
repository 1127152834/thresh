package com.zhang.thresh.gateway.enhance.configure;

import com.zhang.thresh.common.core.entity.constant.ThreshConstant;
import com.zhang.thresh.gateway.enhance.runner.ThreshRouteEnhanceRunner;
import com.zhang.thresh.gateway.enhance.service.BlackListService;
import com.zhang.thresh.gateway.enhance.service.RateLimitRuleService;
import com.zhang.thresh.gateway.enhance.service.RouteEnhanceCacheService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author MrZhang
 */
@EnableAsync
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.zhang.thresh.gateway.enhance.mapper")
@ConditionalOnProperty(name = "thresh.gateway.enhance", havingValue = "true")
public class ThreshRouteEnhanceConfigure {
    @Bean(ThreshConstant.ASYNC_POOL)
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("Thresh-Gateway-Async-Thread");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    public ApplicationRunner threshRouteEnhanceRunner(RouteEnhanceCacheService cacheService,
                                                    BlackListService blackListService,
                                                    RateLimitRuleService rateLimitRuleService) {
        return new ThreshRouteEnhanceRunner(cacheService, blackListService, rateLimitRuleService);
    }
}
