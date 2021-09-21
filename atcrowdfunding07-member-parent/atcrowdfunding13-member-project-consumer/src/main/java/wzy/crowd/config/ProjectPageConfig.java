package wzy.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 网中鱼
 * @date 2021/9/7-17:15
 */
@Configuration
public class ProjectPageConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/to/project-agree").setViewName("project-agree");

        registry.addViewController("/to/project-launch").setViewName("project-launch");

        registry.addViewController("/to/project-return").setViewName("project-return");

        registry.addViewController("/to/project-confirm").setViewName("project-confirm");

        registry.addViewController("/to/project-detail").setViewName("project-detail");

        registry.addViewController("/to/project-success").setViewName("project-success");
    }
}
