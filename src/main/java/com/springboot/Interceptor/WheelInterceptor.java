package com.springboot.Interceptor;

import com.springboot.dao.UserDao;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class WheelInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    private SimpleDateFormat dateFormater;

    public WheelInterceptor(){
        this.dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getServletPath());
        String url = request.getServletPath();
        String token = request.getHeader("Authorization");
        String terminal = request.getHeader("Terminal");
        //检测用户token是否到期
        if (token == null) return false;
        String name = (String) redisTemplate.opsForValue().get(token);
        if (name == null) {
            name = (String) redisTemplate.opsForHash().get(token+"token","name");
            //token到期如未退出，则退出
            if(name != null){
                userService.logout(name,request);
            }
             return false;
        }
        //是否为手机访问
        Boolean ismobile = false;
        if ("mobile".equals(terminal)){
            ismobile = true;
        }
        if (ismobile){
            //记录登录日志
            String ipaddr = request.getRemoteAddr();
            redisTemplate.opsForHash().putIfAbsent(name+"mobtoken","ip",ipaddr);
            redisTemplate.opsForList().leftPush(name+"moblog",url+"="+dateFormater.format(new Date())+"="+ipaddr);
            return true;
        }else {
            //120分钟未发起请求自动下线
            if(redisTemplate.hasKey(token)){
                redisTemplate.expire(token,Duration.ofMinutes(120));
            }
            String ipaddr = request.getRemoteAddr();
            redisTemplate.opsForList().leftPush(name+"log",url+"="+dateFormater.format(new Date())+"="+ipaddr);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
