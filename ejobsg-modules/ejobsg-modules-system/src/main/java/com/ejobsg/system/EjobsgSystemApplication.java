package com.ejobsg.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ejobsg.common.security.annotation.EnableCustomConfig;
import com.ejobsg.common.security.annotation.EnableRyFeignClients;
import com.ejobsg.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 *
 * @author lilu
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class EjobsgSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjobsgSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
