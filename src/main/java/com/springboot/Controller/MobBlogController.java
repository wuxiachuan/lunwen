package com.springboot.Controller;

import com.springboot.domain.Blog;
import com.springboot.domain.Result;
import com.springboot.domain.WheelRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/Blog")
public class MobBlogController {
    @Autowired
    private RedisTemplate redisTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/addBlog")
    @ResponseBody
    public Result addBlog(@RequestBody Blog blog,String publisher){
        System.out.println(blog);
        System.out.println(publisher);
        return new Result(blog,"添加成功",100);
    }
    @RequestMapping("/addComment")
    @ResponseBody
    public Result addBlog(@RequestBody Blog blog,String publisher,String id){

        return new Result(blog,"添加成功",100);
    }
}
