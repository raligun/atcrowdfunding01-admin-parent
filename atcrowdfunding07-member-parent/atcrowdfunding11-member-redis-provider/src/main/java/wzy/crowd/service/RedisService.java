package wzy.crowd.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 网中鱼
 * @date 2021/9/5-16:16
 */
@Service
public interface RedisService {
    boolean deleteByKey(String key);

    String getValueByKey(String key);

    void setKeyValueWithTimeout(String key, String value, long timeout, TimeUnit timeUnit);

    void setKeyValue(String key, String value);
}
