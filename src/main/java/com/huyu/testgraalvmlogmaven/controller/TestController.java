package com.huyu.testgraalvmlogmaven.controller;

import com.huyu.testgraalvmlogmaven.factory.TestA;
import com.huyu.testgraalvmlogmaven.mapper.TestMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("test")
@RequestMapping("/test")
public class TestController {

  private static final Logger log = LoggerFactory.getLogger(TestController.class);

  @Autowired
  TestMapper testMapper;

  //
  @Autowired
  TestA testA;

  @Autowired
  RocketMQTemplate rocketMQTemplate;

  @Autowired
  RedisTemplate redisTemplate;

  @GetMapping("/get")
  @ResponseBody
  public Map<String, String> test() {
    testMapper.select();
    System.out.println(testA);
    redisTemplate.opsForHash().put("test", "test", "test");
    rocketMQTemplate.sendOneWay("test", "test");
    Map<String, String> ob = new HashMap<>();
    String str = Optional.ofNullable(redisTemplate.opsForHash().get("test", "test"))
        .orElse("test-null").toString();
    ob.put("test", str);
    return ob;
  }
}
