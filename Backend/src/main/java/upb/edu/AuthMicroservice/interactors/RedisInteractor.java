package upb.edu.AuthMicroservice.interactors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import upb.edu.AuthMicroservice.services.RedisService;

@Component
public class RedisInteractor {

    @Autowired
    private RedisService redisService;

    public void saveKeyValue(String key, String value) {
        redisService.set(key, value);
    }
}
