package wzy.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 网中鱼
 * @date 2021/9/4-23:20
 */
@SpringBootApplication
@EnableEurekaServer
public class eurekaMain {
    public static void main(String[] args) {
        try {

            SpringApplication.run(eurekaMain.class,args);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
