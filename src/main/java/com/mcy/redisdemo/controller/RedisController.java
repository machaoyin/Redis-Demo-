package com.mcy.redisdemo.controller;

import com.mcy.redisdemo.entity.Stu;
import com.mcy.redisdemo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取对应键的值，redisTemplate操作
     * @param key
     * @return
     */
    @RequestMapping(value = "get")
    public Object getValue(String key){
        Object value = redisTemplate.opsForValue().get(key);
        return value;
    }

    /**
     * 添加键值
     * @param key
     * @param value
     * @return
     */
    @RequestMapping(value = "set")
    public boolean set(String key, String value){
        return redisUtil.set(key, value);
    }

    /**
     * 添加List对象数据到redis中
     * @return
     */
    @RequestMapping(value = "/setList")
    public boolean setList(){
        List<Object> stuList = new ArrayList<>();
        Stu stu = new Stu();
        stu.setId(1);
        stu.setAge(18);
        stu.setDept("软件部");
        stu.setName("张三");

        Stu stu1 = new Stu();
        stu1.setId(2);
        stu1.setAge(18);
        stu1.setDept("软件部");
        stu1.setName("王五");

        Stu stu2 = new Stu();
        stu2.setId(3);
        stu2.setAge(18);
        stu2.setDept("财务部");
        stu2.setName("李四");

        stuList.add(stu);
        stuList.add(stu1);
        stuList.add(stu2);
        return redisUtil.lSetList("stu", stuList);
    }

    /**
     * 获取全部数据
     * @return
     */
    @RequestMapping(value = "getList")
    public Object getList(){
        return redisUtil.lGet("stu", 0, -1);
    }

}
