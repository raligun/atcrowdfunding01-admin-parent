package wzy.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 网中鱼
 * @date 2021/9/5-18:02
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class AuthMain {
    public static void main(String[] args) {
        SpringApplication.run(AuthMain.class,args);
    }
}
