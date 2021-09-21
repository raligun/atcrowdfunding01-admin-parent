package wzy.crowd.config;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import wzy.crowd.exception.AccessForbiddenException;
import wzy.crowd.exception.AccountExistException;
import wzy.crowd.exception.IllegalFieldException;
import wzy.crowd.exception.LoginFailedException;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.CrowdUtil;
import wzy.crowd.utils.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 网中鱼
 * @date 2021/8/26-17:31
 */
@ControllerAdvice
public class ExceptionResolverConfig {


    // 处理非法参数的异常
    @ExceptionHandler(IllegalFieldException.class)
    public ModelAndView IllegalFieldExceptionResolver(IllegalFieldException exception,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws IOException {
        String viewName = "admin_add";
        return handleException(exception,request,response,viewName);
    }

    @ExceptionHandler(AccountExistException.class)
    public ModelAndView AccountExistExceptionResolver(AccountExistException exception,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws IOException {
        String viewName = exception.getViewName();
        return handleException(exception,request,response,viewName);
    }

    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView AccessForbiddenExceptionResolver(AccessForbiddenException exception){

        String viewName = "admin_login";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(Constant.ATTR_NAME_EXCEPTION, exception);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView LoginFailedExceptionResolver(LoginFailedException exception,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws IOException {
        String viewName = "admin_login";
        return handleException(exception,request,response,viewName);
    }



    private ModelAndView handleException(Exception exception,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   String viewName) throws IOException {
        // 1. 判断当前请求类型
        if (CrowdUtil.judgeIsAjax(request)){
                // 2. 如果为Ajax请求
                // 3. 创建 ResultSet 对象
                ResultSet resultSet = ResultSet.error();
                resultSet.setMessage(exception.getMessage());
                // 4. 创建Gson对象
                Gson gson = new Gson();
                // 5. 将ResultEntity对象转换为JSON字符串
                String json = gson.toJson(resultSet);
                // 6. 将JSON字符作为响应体返回给浏览器
                response.getWriter().write(json);
                // 7. 上面已经通过原生response对象返回了响应，因此不再提供ModelAndView对象
                return null;
            }
            // 8. 如果不是Ajax请求，则创建ModelAndView对象
            ModelAndView modelAndView = new ModelAndView();
            // 9. 将Exception对象存入模型
            modelAndView.addObject(Constant.ATTR_NAME_EXCEPTION, exception);
            // 10. 设置对应的视图名称
            modelAndView.setViewName(viewName);
            return modelAndView;
        }

}
