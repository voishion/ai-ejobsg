package com.ejobsg.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ejobsg.common.security.annotation.EnableCustomConfig;
import com.ejobsg.common.security.annotation.EnableEjFeignClients;
import com.ejobsg.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 *
 * @author lilu
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableEjFeignClients
@SpringBootApplication
public class EjobsgGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjobsgGenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
