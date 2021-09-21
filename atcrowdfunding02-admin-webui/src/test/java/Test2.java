import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import wzy.crowd.utils.CrowdUtil;

import java.sql.PseudoColumnUsage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 网中鱼
 * @date 2021/8/26-22:18
 */
public class Test2 {

    @Test
    public void test1(){
        String admin = CrowdUtil.md5("admin");

        System.out.println(admin);
    }

    @Test
    public void test2(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }
    
    @Test
    public void test3() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String date = addDate(dateFormat.format(new Date()), 30);
        System.out.println(date);
    }

    public static String addDate(String date,int days) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
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

