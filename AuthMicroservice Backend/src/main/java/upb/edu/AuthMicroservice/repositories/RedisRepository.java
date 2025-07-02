package upb.edu.AuthMicroservice.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void save(String key, String value, long ttlMinutes) {
        redisTemplate.opsForValue().set(key, value, ttlMinutes, TimeUnit.MINUTES);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
