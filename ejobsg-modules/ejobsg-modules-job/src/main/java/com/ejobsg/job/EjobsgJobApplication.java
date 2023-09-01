package com.ejobsg.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ejobsg.common.security.annotation.EnableCustomConfig;
import com.ejobsg.common.security.annotation.EnableEjFeignClients;
import com.ejobsg.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 *
 * @author lilu
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableEjFeignClients
@SpringBootApplication
public class EjobsgJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjobsgJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
