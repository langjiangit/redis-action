package com.example.cluster.controller;

import com.example.cluster.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description:TODO
 * @Author:irving
 * @Date:2020/7/3 9:33 上午
 **/
@RestController
@RequestMapping("sentinel")
public class TestController {

    @Autowired
    RedisService redisService;


    @PostMapping("set")
    public String put(@RequestBody Map map){
        redisService.set(map.get("key").toString(),map.get("val"));
        return "200";
    }


    @GetMapping("get")
    public Object get(@RequestParam Map map){
      return redisService.get(map.get("key").toString());
    }



}
