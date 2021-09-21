package wzy.crowd.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 网中鱼
 * @date 2021/8/26-17:11
 */
public class CrowdUtil {
    /**
     * 判断是不是ajax请求
     * @param request
     * @return 是返回ture
     */
    public static boolean judgeIsAjax(HttpServletRequest request){
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Request-With");

        // 2. 判断
        return (acceptHeader != null && acceptHeader.contains("application/json"))
                || (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }

    /**
     * md5 加密算法
     * @param source 原密码
     * @return 加密后的密码
     */
    public static String md5(String source){
        if (source == null || source.length() == 0){
            throw new RuntimeException(Constant.MESSAGE_STRING_INVALIDATE);
        }
        try {
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] input = source.getBytes();
            //加密
            byte[] output = messageDigest.digest(input);
            int signum = 1;//表示转成正数
            BigInteger bigInteger = new BigInteger(signum,output);
            //toString()转化为16进制
            int radix = 16;
            String encoded = bigInteger.toString(radix);

            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *创建该文件所在的路径
     * @param file
     * @throws IOException
     */
    public static void mkdir(File file) throws IOException {
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * 计算经过特定天数后的日期
     * @param date 起始日期 格式 yyyy-MM-dd HH:mm:ss
     * @param days 多少天后
     * @return 多少天后的日期
     * @throws ParseException
     */
    public static String addDate(String date,int days) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(Constant.ATTR_DATE_PATTERN);
        Date parse = format.parse(date);
        calendar.setTime(parse);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // days  多少天后的日期
        int newDay = day+days;
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,newDay);
        return format.format(calendar.getTime());
    }
}
