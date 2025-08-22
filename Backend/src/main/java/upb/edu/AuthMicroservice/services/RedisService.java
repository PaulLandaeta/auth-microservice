package upb.edu.AuthMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.edu.AuthMicroservice.repositories.RedisRepository;

@Service
public class RedisService {

    @Autowired
    private RedisRepository redisRepository;

    public void set(String key, String value) {
        redisRepository.save(key, value, 5); 
    }

    public String get(String key) {
        return redisRepository.get(key);
    }
}
