package wzy.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 网中鱼
 * @date 2021/9/5-13:45
 */
@EnableEurekaClient
@SpringBootApplication
public class RedisMain {
    public static void main(String[] args) {
        SpringApplication.run(RedisMain.class,args);
    }
}
