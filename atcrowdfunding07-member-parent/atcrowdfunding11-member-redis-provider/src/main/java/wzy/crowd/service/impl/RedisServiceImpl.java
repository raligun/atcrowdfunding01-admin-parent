package wzy.crowd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import wzy.crowd.service.RedisService;

import java.util.concurrent.TimeUnit;

/**
 * @author 网中鱼
 * @date 2021/9/5-16:17
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean deleteByKey(String key) {
        Boolean b = stringRedisTemplate.delete(key);
        return b;
    }

    @Override
    public String getValueByKey(String key) {
        ValueOperations<String, String> ops =
                stringRedisTemplate.opsForValue();

        String value = ops.get(key);
        return value;
    }

    @Override
    public void setKeyValueWithTimeout(String key, String value, long timeout, TimeUnit timeUnit) {
        ValueOperations<String, String> ops =
                stringRedisTemplate.opsForValue();
        ops.set(key,value,timeout,timeUnit);
    }

    @Override
    public void setKeyValue(String key, String value) {
        ValueOperations<String, String> ops =
                stringRedisTemplate.opsForValue();
        ops.set(key,value);

    }
}
