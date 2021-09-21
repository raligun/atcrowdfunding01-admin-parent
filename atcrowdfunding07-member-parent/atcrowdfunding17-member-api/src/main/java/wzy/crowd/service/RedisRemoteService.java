package wzy.crowd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wzy.crowd.utils.ResultSet;

import java.util.concurrent.TimeUnit;

/**
 * @author 网中鱼
 * @date 2021/9/5-15:59
 */
@FeignClient("wzy-crowd-redis")
@Controller
public interface RedisRemoteService {

    /**
     * 在 Redis 设置键值对
     * @param key
     * @param value
     * @return 返回的结果集
     */
    @RequestMapping("consumer/setRedis/keyValue")
    public ResultSet setRedisKeyValue(@RequestParam("key")String key,
                                      @RequestParam("value")String value);

    /**
     * 在 Redis 设置键值对和其过期时间
     * @param key
     * @param value
     * @param timeout 默认毫秒
     * @param timeUnit 可以自定义过其时间单位
     * @return
     */
    @RequestMapping("consumer/setRedis/keyValueWithTimeout")
    public ResultSet setRedisKeyValueWithTimeout(@RequestParam("key")String key,
                                                 @RequestParam("value")String value,
                                                 @RequestParam("timeout")long timeout,
                                                 @RequestParam(value = "TimeUnit",defaultValue = "MILLISECONDS")TimeUnit timeUnit);

    /**
     * 根据 key 获取 redis 中的值
     * @param key
     * @return
     */
    @RequestMapping("consumer/getRedisByKey")
    public ResultSet getRedisByKey(@RequestParam("key")String key);

    /**
     * 根据 key 删除 redis 中的值
     * @param key
     * @return
     */
    @RequestMapping("consumer/deleteRedisByKey")
    public ResultSet deleteRedisByKey(@RequestParam("key")String key);
}
