package upb.edu.AuthMicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upb.edu.AuthMicroservice.services.RedisService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redis-test")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> setKeyValue(@RequestBody Map<String, String> request) {
        String key = request.get("key");
        String value = request.get("value");

        redisService.set(key, value);

        Map<String, Object> response = new HashMap<>();
        response.put("code", 201);
        response.put("msg", "Valor guardado correctamente");

        return ResponseEntity.status(201).body(response);
    }
}
