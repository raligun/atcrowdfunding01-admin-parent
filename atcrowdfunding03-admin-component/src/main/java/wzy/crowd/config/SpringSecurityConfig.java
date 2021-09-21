package wzy.crowd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import wzy.crowd.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 网中鱼
 * @date 2021/9/1-17:11
 */
@Configuration
@EnableWebSecurity
// 若 spring 和 springMVC 的ioc容器分开则很可能失效
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled = true) //开启全局注解权限访问限制
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication().
//                    passwordEncoder(new BCryptPasswordEncoder()).
//                    withUser("admin").password("$2a$10$RlOYlN0XHlzGHnoILqyuxenyn67o.59Gh0ughj/qoXR72d5uLZWrS").roles("admin");

        // 自定义用户权限处理细节（从数据库查询）
        auth.userDetailsService(myUserService).
                passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests().
                antMatchers(        // 放行静态资源；/** 代表多层目录
                        "/admin_login","/bootstrap/**",
                        "/css/**","/fonts/**","/img/**","/jquery/**",
                        "/layer/**","/myjs/**","/script/**","/ztree/**").
                permitAll().
                anyRequest().
                authenticated().
                and().
                // 禁用csrf；（跨域安全）
                csrf().
                disable().
                // 指定登录页面，若访问的资源需要登录即自动跳转去
                formLogin().
                loginPage("/admin_login").
                loginProcessingUrl("/do/login").
                permitAll().//!!! 不要忘记加
                // 告诉spring security用户名和密码的前端的name
                usernameParameter("loginAcct").
                passwordParameter("userPswd").
                defaultSuccessUrl("/admin_main").
                and().
                // 注销登录的url
                logout().
                logoutUrl("/do/logout").
                logoutSuccessUrl("/admin_login").
                and().
                exceptionHandling().
                // 指定异常处理界面
                accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                httpServletRequest.setAttribute(Constant.ATTR_NAME_EXCEPTION,e);
                httpServletRequest.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(httpServletRequest,httpServletResponse);
            }
        });
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

