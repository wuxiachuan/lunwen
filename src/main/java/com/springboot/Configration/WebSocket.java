package com.springboot.Configration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocket {
    public static int onlineNumber = 0;
    private static Map<String,WebSocket> clients = new ConcurrentHashMap<>();
    private Session session;
    private String username;
    @OnOpen
    public void onOpen(@PathParam("username")String username,Session session){
        onlineNumber++;
        this.username = username;
        this.session = session;
        try{
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            //先给所有人发送通知，说我上线了
            Map<String,Object> map1 = new HashMap<>();
            map1.put("messageType","1");
            map1.put("username",username);
            sendMessageAll(JSON.toJSONString(map1),username);
            clients.put(username,this);
            //给自己发一条消息：告诉自己现在都有谁在线
            Map<String,Object> map2 = new HashMap<>();
            map2.put("messageType","3");
            Set<String> set = clients.keySet();
            //移除自己
            List res = new ArrayList<String>();
            for (String name : set){
                if (!username.equals(name)){
                    res.add(name);
                }
            }
            map2.put("onlineUsers",res);
            sendMessageTo(JSON.toJSONString(map2),username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        //error.printStackTrace();
    }

    @OnClose
    public void onClose()
    {
        onlineNumber--;
        clients.remove(username);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String,Object> map1 =new HashMap<String,Object>();
            map1.put("messageType","2");
            map1.put("username",username);
            sendMessageAll(JSON.toJSONString(map1),username);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session)
    {
        try {
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String fromusername = jsonObject.getString("from");
            String tousername = jsonObject.getString("to");
            System.out.println("message: "+textMessage);
            //如果不是发给所有，那么就发给某一个人
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String,Object> map1 =new HashMap<String,Object>();
            map1.put("messageType","4");
            map1.put("textMessage",textMessage);
            map1.put("fromusername",fromusername);
            if(tousername.equals("All")){
                map1.put("tousername","all");
                sendMessageAll(JSON.toJSONString(map1),fromusername);
            }
            else{
                map1.put("tousername",tousername);
                sendMessageTo(JSON.toJSONString(map1),tousername);
            }
        }
        catch (Exception e){
        }
    }

    public void sendMessageTo(String message, String ToUserName) throws IOException {
        System.out.println("sendto");
        for (WebSocket item : clients.values()) {
            if (item.username.equals(ToUserName)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }
    public void sendMessageAll(String message,String FromUserName) throws IOException {
        System.out.println("sendAll");
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }

}
