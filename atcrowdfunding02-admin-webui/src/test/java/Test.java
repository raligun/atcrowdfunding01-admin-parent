import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.util.calendar.BaseCalendar;
import wzy.crowd.entity.Admin;
import wzy.crowd.entity.Role;
import wzy.crowd.mapper.AdminMapper;
import wzy.crowd.service.AdminService;
import wzy.crowd.service.AuthService;
import wzy.crowd.service.RoleService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * @author 网中鱼
 * @date 2021/8/25-19:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-persist-mybatis.xml")
public class Test {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @org.junit.Test
    public void test() throws SQLException {
//        Connection connection = dataSource.getConnection();
//
//        Admin admin = new Admin("wzy", "123",
//                "网中鱼", "wzy@qq.com",
//                new Date().toString().substring(0,19));
//        int i = adminMapper.insert(admin);
//        System.out.println(i);
//        Admin admin = adminMapper.selectByPrimaryKey(1);
//
//        System.out.println(admin);

//        Logger logger = LoggerFactory.getLogger(Test.class);
//        logger.debug("debug--");
//        logger.info("info--");
//        logger.warn("warning--");
//        logger.error("error--");
//        for (int i = 0; i < 50; i++) {
//            adminService.saveAdmin(new Admin("loginAcct"+i,"loginAcct"+i,
//                    "userName"+i,"userName"+i+"@qq.com",null));
//
//        }
//        System.out.println("插入完成");

//        PageInfo<Role> pageInfo = roleService.getPageInfo(1, 5, "key");

//        for (int i = 0; i < 10; i++) {
//            roleService.saveRole(new Role("role"+i));
//        }


//        List<String> list = roleService.getRoleByAdminId(15);
//        System.out.println(list);

        System.out.println(authService.getAuthByAdminId(15));
        System.out.println("完成！");

    }
}
