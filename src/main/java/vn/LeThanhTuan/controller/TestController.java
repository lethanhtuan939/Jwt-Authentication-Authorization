package vn.LeThanhTuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.LeThanhTuan.cache.RedisService;

@RestController
@RequestMapping("/api/redis")
public class TestController {
    @Autowired
    RedisService redisService;

    @GetMapping("/set")
    public String setKey(String key, String value) {
        redisService.setKey("key", "Tuan");
        return "Key set successfully!";
    }

    @GetMapping("/get/{key}")
    public String getKey(@PathVariable String key) {
        String value = (String) redisService.getKey(key);
        return "Value for key " + key + ": " + value;
    }
}
