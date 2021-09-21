package wzy.crowd.utils;

import java.util.UUID;

/**
 * @author 网中鱼
 * @date 2021/9/5-22:25
 */
public class CodeUtil {

    public static String getCode(){
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
