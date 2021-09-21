package wzy.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 网中鱼
 * @date 2021/9/5-10:18
 */
@MapperScan("wzy.crowd.mapper")
@SpringBootApplication
@EnableEurekaClient
public class MysqlMain {
    public static void main(String[] args) {
        SpringApplication.run(MysqlMain.class,args);
    }
}
