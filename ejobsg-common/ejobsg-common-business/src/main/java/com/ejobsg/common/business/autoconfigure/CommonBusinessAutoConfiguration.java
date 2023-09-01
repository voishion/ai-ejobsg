package com.ejobsg.common.business.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配类
 *
 * @author lilu
 */
@Configuration
@ComponentScan(basePackages = "com.ejobsg.common.business")
public class CommonBusinessAutoConfiguration {
}
