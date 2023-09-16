package com.ejobsg.common.security.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * 系统配置
 *
 * @author lilu
 */
@Slf4j
public class ApplicationConfig {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    /**
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());

            DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN);
            // 返回日期数据序列化
            jacksonObjectMapperBuilder.serializerByType(LocalDate.class, new LocalDateSerializer(localDateFormatter));
            // 接收日期数据反序列化
            jacksonObjectMapperBuilder.deserializerByType(LocalDate.class, new LocalDateDeserializer(localDateFormatter));

            DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            // 返回日期时间数据序列化
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(localDateTimeFormatter));
            // 接收日期时间数据反序列化
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(localDateTimeFormatter));
        };
    }
}
