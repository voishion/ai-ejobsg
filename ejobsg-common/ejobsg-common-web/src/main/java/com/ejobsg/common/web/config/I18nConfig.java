package com.ejobsg.common.web.config;

import com.ejobsg.common.web.core.I18nLocaleResolver;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 国际化配置
 *
 * @author lilu
 */
@Configuration
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class I18nConfig {

    @Bean
    @ConditionalOnMissingBean(LocaleResolver.class)
    public LocaleResolver localeResolver() {
        return new I18nLocaleResolver();
    }

}
