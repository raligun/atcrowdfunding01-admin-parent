package wzy.crowd.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wzy.crowd.service.RedisService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.ResultSet;

import java.util.concurrent.TimeUnit;

/**
 * @author 网中鱼
 * @date 2021/9/5-16:09
 */
@RestController
public class SimpleRedisController {
    @Autowired
    private RedisService redisService;

    private Logger logger = LoggerFactory.getLogger(SimpleRedisController.class);


    @RequestMapping("consumer/setRedis/keyValue")
    public ResultSet setRedisKeyValue(@RequestParam("key") String key,
                                      @RequestParam("value") String value) {
        try {
            redisService.setKeyValue(key, value);
            return ResultSet.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultSet.error(e.getMessage());
        }
    }

    @RequestMapping("consumer/setRedis/keyValueWithTimeout")
    public ResultSet setRedisKeyValueWithTimeout(@RequestParam("key") String key,
                                                 @RequestParam("value") String value,
                                                 @RequestParam("timeout") long timeout,
                                                 @RequestParam(value = "TimeUnit", defaultValue = "MILLISECONDS") TimeUnit timeUnit) {

        try {
            redisService.setKeyValueWithTimeout(key, value, timeout, timeUnit);

            return ResultSet.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultSet.error(e.getMessage());
        }
    }

    @RequestMapping("consumer/getRedisByKey")
    public ResultSet getRedisByKey(@RequestParam("key") String key) {

        try {
            String value = redisService.getValueByKey(key);

            return ResultSet.success().addData(value);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultSet.error(e.getMessage());
        }
    }


    @RequestMapping("consumer/deleteRedisByKey")
    public ResultSet deleteRedisByKey(@RequestParam("key") String key) {
        try {
            boolean b = redisService.deleteByKey(key);

            if (b) {
                return ResultSet.success();
            } else {
                logger.info(Constant.MESSAGE_SOMETHING_MISSING);
                return ResultSet.error(Constant.MESSAGE_SOMETHING_MISSING);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultSet.error(e.getMessage());
        }
    }
}
