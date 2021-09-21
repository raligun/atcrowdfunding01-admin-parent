import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import wzy.crowd.RedisMain;

/**
 * @author 网中鱼
 * @date 2021/9/5-13:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisMain.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

   @Test
   public void test1(){
       ValueOperations<String, String> opsForValue =
               stringRedisTemplate.opsForValue();
       opsForValue.set("banana","yellow");
   }
}
