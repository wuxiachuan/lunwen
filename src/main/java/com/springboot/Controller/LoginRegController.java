package com.springboot.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRegController {
    /**
     * 如果自动跳转到这个页面，说明用户未登录，返回相应的提示即可
     * <p>
     * 如果要支持表单登录，可以在这个方法中判断请求的类型，进而决定返回JSON还是HTML页面
     *
     * @return
     */
    @RequestMapping("/login_page")
    public String loginPage() {
        return "error,尚未登录，请登录!";
    }
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String test(String name,String passwd) {
        return name+":"+passwd;
    }
}
