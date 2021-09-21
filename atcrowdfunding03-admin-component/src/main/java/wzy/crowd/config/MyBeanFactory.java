package wzy.crowd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 网中鱼
 * @date 2021/9/2-9:58
 */
@Configuration
public class MyBeanFactory {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        // 盐值加密
        return new BCryptPasswordEncoder();
    }


}
