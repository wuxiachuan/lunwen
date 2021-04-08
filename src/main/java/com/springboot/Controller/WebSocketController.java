package com.springboot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {
    @RequestMapping("/websocket/{name}")
    public void webSocket(@PathVariable String name, Model model){
        try{
            System.out.println(name);
            //通过Model进行对象数据的传递
            model.addAttribute("username",name);
            return;
        }
        catch (Exception e){
            return;
        }
    }

}
