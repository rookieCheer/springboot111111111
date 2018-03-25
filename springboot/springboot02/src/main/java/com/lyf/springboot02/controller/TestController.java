package com.lyf.springboot02.controller;

import com.lyf.springboot02.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @RestController 相当于@Controller + @ResponseBody
 * 这里使用@Controller 返回String 跳转到这个页面
 *
 * 返回Object 需要使用  @ResponseBody注解
 *      下面是实际开发常用的返回类型
 */
@Controller
public class TestController {


    /**
     * 返回到页面上
     * @return
     */
    @RequestMapping("/first")
    public  String first(){
        return "first";
    }
    /**
     * 返回字符串
     * @return
     */
    @RequestMapping("/second")
    @ResponseBody
    public String second(){
        return "second";
    }
    /**
     * 返回map
     * @return
     */
    @RequestMapping("/third")
    @ResponseBody
    public Map<String,Object> third(){
        Map<String,Object> map = new HashMap();
        map.put("a","a");
        map.put("a1","a1");
        map.put("a2","a2");
        return map;
    }

    /**
     * 返回List
     * @return
     */
    @RequestMapping("/fourth")
    @ResponseBody
    public List<String> fourth(){
        List<String> list = new ArrayList();
        list.add("a");
        list.add("a1");
        list.add("a2");
        return list;
    }

    /**
     * 返回user对象
     * @return
     */
    @RequestMapping("/fifth")
    @ResponseBody
    public User fifth(){
        User user = new User();
        user.setName("张三");
        user.setPwd("123456");
        return user;
    }
    /**
     * 返回list集合放user对象
     * @return
     */
    @RequestMapping("/sixth")
    @ResponseBody
    public List<User> sixth(){
        List<User> list = new ArrayList();
        User user = new User();
        user.setName("张三");
        user.setPwd("123456");
        User user1 = new User();
        user1.setName("张三1");
        user1.setPwd("1234561");
        list.add(user);
        list.add(user1);
        return list;
    }
    /**
     * 返回map放user对象
     * @return
     */
    @RequestMapping("/seventh")
    @ResponseBody
    public Map<String,Object> seventh(){
        Map<String,Object> map = new HashMap();
        User user = new User();
        user.setName("张三");
        user.setPwd("123456");
        User user1 = new User();
        user1.setName("张三1");
        user1.setPwd("1234561");
        map.put("user",user);
        map.put("user1",user1);
        return map;
    }

}
