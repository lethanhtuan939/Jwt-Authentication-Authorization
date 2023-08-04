package vn.LeThanhTuan.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    public void setKey(Object key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getKey(Object key) {
        return redisTemplate.opsForValue().get(key);
    }
}
