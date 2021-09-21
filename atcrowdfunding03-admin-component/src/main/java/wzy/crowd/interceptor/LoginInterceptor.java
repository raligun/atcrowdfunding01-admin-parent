package wzy.crowd.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import wzy.crowd.entity.Admin;
import wzy.crowd.exception.AccessForbiddenException;
import wzy.crowd.utils.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 做登录检查的方法，被spring security代替
 * @author 网中鱼
 * @date 2021/8/27-13:47
 */
//@Controller
//public class LoginInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//
//        Admin admin = (Admin) session.getAttribute(Constant.ATTR_NAME_LOGIN_ADMIN);
//        if (admin==null){
//            throw new AccessForbiddenException(Constant.MESSAGE_ACCESS_FORBIDDEN);
//        }
//
//        return true;
//    }
//
//}
