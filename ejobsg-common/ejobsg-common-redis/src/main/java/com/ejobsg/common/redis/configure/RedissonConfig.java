package com.ejobsg.common.redis.configure;

import cn.hutool.core.util.StrUtil;
import com.ejobsg.common.redis.service.RedissonLockService;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置
 *
 * @author lilu
 */
@Configuration
@ConditionalOnClass(RedissonAutoConfiguration.class)
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password:''}")
    private String password;

    @Value("${spring.redis.database:0}")
    private Integer database;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redissonClient() {
        String url = StrUtil.format("redis://{}:{}", host, port);
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(url);
        singleServerConfig.setDatabase(database);
        if (StrUtil.isNotBlank(password)) {
            singleServerConfig.setPassword(password);
        }
        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnBean(RedissonClient.class)
    public RedissonLockService redissonLockService(RedissonClient redissonClient) {
        return new RedissonLockService(redissonClient);
    }

}
