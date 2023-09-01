package com.ejobsg.recruitment;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ejobsg.common.security.annotation.EnableCustomConfig;
import com.ejobsg.common.security.annotation.EnableEjFeignClients;
import com.ejobsg.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 招聘模块
 *
 * @author lilu
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableEjFeignClients
@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
@MapperScan("com.ejobsg.recruitment.mapper")
public class EjobsgRecruitmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjobsgRecruitmentApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  招聘模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
