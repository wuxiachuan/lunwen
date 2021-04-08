package com.springboot.Controller;

import com.springboot.domain.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    private SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @MessageMapping("/ws/chat")
    public void handleMsg(ChatMsg chatMsg) {
        chatMsg.setFrom("admin");
        chatMsg.setFromNickname("admin");
        chatMsg.setDate(dateFormater.format(new Date()));
        System.out.println(chatMsg);
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }
    @MessageMapping("/all")
    public void brocast(Principal principal, ChatMsg chatMsg) {
        chatMsg.setFrom("admin");
        chatMsg.setFromNickname("admin");
        chatMsg.setTo("zhangshan");
        chatMsg.setDate(dateFormater.format(new Date()));
        System.out.println(chatMsg);
        System.out.println(principal.getName());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
        //simpMessagingTemplate.convertAndSend("/queue/chat",chatMsg);
    }
}
