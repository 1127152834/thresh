package com.zhang.thresh.common.redis.starter.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.thresh.common.redis.starter.properties.ThreshLettuceRedisProperties;
import com.zhang.thresh.common.redis.starter.service.RedisService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ConditionalOnClass 某个class位于类路径上，才会实例化一个Bean
 * @ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
 * @ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
 * @ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
 * @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
 * @ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
 * @ConditionalOnNotWebApplication（不是web应用）
 */

/**
 * Lettuce Redis配置
 *
 * @author MrZhang
 */
@EnableConfigurationProperties(ThreshLettuceRedisProperties.class)
@ConditionalOnProperty(value = "thresh.lettuce.redis.enable", havingValue = "true", matchIfMissing = true)
public class ThreshLettuceRedisAutoConfigure {

    @Bean(name = "redisTemplate")
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * 仅在当前上下文中含有redisTemplate对象时才能获取到redisService实例。 因为redisService需要有redisTemplate才能操作
     * @return
     */
    @Bean
    @ConditionalOnBean(name = "redisTemplate")
    public RedisService redisService() {
        return new RedisService();
    }

}
