package wzy.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 网中鱼
 * @date 2021/9/5-18:58
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulMain {
    public static void main(String[] args) {
        SpringApplication.run(ZuulMain.class,args);
    }
}
