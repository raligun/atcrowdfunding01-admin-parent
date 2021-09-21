package wzy.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 网中鱼
 * @date 2021/9/10-23:01
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class PayMain {
    public static void main(String[] args) {
        SpringApplication.run(PayMain.class,args);
    }
}
