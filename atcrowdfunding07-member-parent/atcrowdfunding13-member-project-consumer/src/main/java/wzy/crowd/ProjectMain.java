package wzy.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 网中鱼
 * @date 2021/9/7-17:08
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ProjectMain {
    public static void main(String[] args) {
        SpringApplication.run(ProjectMain.class,args);
    }
}
