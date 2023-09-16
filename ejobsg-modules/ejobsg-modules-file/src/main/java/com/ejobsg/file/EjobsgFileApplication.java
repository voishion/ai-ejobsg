package com.ejobsg.file;

import com.ejobsg.common.security.annotation.EnableCustomConfig;
import com.ejobsg.common.security.annotation.EnableEjFeignClients;
import com.ejobsg.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 文件服务
 *
 * @author lilu
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableEjFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EjobsgFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjobsgFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
