package wzy.crowd.config;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import wzy.crowd.utils.CodeUtil;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.ResultSet;

/**
 * @author 网中鱼
 * @date 2021/9/6-18:04
 */
@Configuration
public class MyBeanFactory {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
