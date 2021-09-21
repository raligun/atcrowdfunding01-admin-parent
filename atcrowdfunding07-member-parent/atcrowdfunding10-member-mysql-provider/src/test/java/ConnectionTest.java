import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wzy.crowd.MysqlMain;
import wzy.crowd.mapper.MemberPOMapper;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
;

/**
 * @author 网中鱼
 * @date 2021/9/5-10:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MysqlMain.class)
public class ConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;
    
    private Logger logger = LoggerFactory.getLogger(ConnectionTest.class);
    @Test
    public void test1(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.getMessage();
        }

        logger.info(connection.getClass().toString());

//        List<MemberPO> memberPOS = memberPOMapper.selectByExample(null);
//
//        logger.info(memberPOS.toString());
    }
}
