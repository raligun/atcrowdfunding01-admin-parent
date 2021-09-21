package wzy.crowd.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 网中鱼
 * @date 2021/9/7-11:18
 */
public class NoFilterResourceRule {
    private static final Set<String> PASS_RES_SET = new HashSet<>();
    static {
        PASS_RES_SET.add("/");
        PASS_RES_SET.add("/get/code");
        PASS_RES_SET.add("/get/emailCode");
        PASS_RES_SET.add("/do/member/emailRegister");
        PASS_RES_SET.add("/do/member/register");
        PASS_RES_SET.add("/do/login");
        PASS_RES_SET.add("/do/register");
        PASS_RES_SET.add("/to/member_login");
        PASS_RES_SET.add("/to/member_reg_email");
        PASS_RES_SET.add("/project/get/detail/projectInfo");
    }
    private static final Set<String> STATIC_RES_SET = new HashSet<>();
    static {
        STATIC_RES_SET.add("bootstrap");
        STATIC_RES_SET.add("css");
        STATIC_RES_SET.add("fonts");
        STATIC_RES_SET.add("img");
        STATIC_RES_SET.add("jquery");
        STATIC_RES_SET.add("layer");
        STATIC_RES_SET.add("script");
        STATIC_RES_SET.add("ztree");
        STATIC_RES_SET.add("projectImg");
    }

    /**
     * 传入请求的路径
     * 判断资源是否应该被过滤
     * @return 应该被过滤返回true，应该放行返回false
     */
    public static boolean judgeResourceShouldFilter(String requestPath){
        boolean resourceRes = PASS_RES_SET.contains(requestPath);
        if (resourceRes){
            return false;
        }

        String[] split = requestPath.split("/");
        String firstPath = split[1];

        boolean staticRes = STATIC_RES_SET.contains(firstPath);
        if (staticRes){
            return false;
        }

        return true;

    }
}
