package com.ejobsg.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 *
 * @author lilu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EjobsgGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjobsgGatewayApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统网关启动成功   ლ(´ڡ`ლ)ﾞ");
    }

}
