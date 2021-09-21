package wzy.crowd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.NoFilterResourceRule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 网中鱼
 * @date 2021/9/7-11:08
 */
@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 通过获取 request 和 session 获取登录用户
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Object loginMember = request.getSession().getAttribute(Constant.ATTR_NAME_LOGIN_MEMBER);
        // 如果已经登录就不需要过滤
        if (loginMember != null){
            return false;
        }

        String requestPath = request.getServletPath();
        // 判断不需要过滤的页面 和 不需要过滤的静态资源
        return NoFilterResourceRule.judgeResourceShouldFilter(requestPath);
    }


    @Override
    public Object run() throws ZuulException {
        // 通过获取 request 和 session 获取登录用户
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpSession session = request.getSession();
        Object loginMember = session.getAttribute(Constant.ATTR_NAME_LOGIN_MEMBER);
        if (loginMember == null){
            HttpServletResponse response = requestContext.getResponse();
            session.setAttribute(Constant.MESSAGE,Constant.MESSAGE_ACCESS_FORBIDDEN);
//            request.setAttribute(Constant.MESSAGE,Constant.MESSAGE_ACCESS_FORBIDDEN);
//            request.getRequestDispatcher("to/member_login").forward(request,response);
            try {
                response.sendRedirect(Constant.REDIRECT_TITLE+"to/member_login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
