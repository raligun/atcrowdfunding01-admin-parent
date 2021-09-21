package wzy.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 网中鱼
 * @date 2021/9/5-21:30
 */
@Configuration
public class PageConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/do/register").setViewName("member_reg");

        registry.addViewController("/to/member_login").setViewName("member_login");

        registry.addViewController("/to/member_center").setViewName("member_center");

        registry.addViewController("/to/member_reg_email").setViewName("member_reg_email");

        registry.addViewController("/to/member_minecrowdfunding").setViewName("member_minecrowdfunding");

    }
}
