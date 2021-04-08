package com.springboot;

import com.mysql.cj.util.TimeUtil;
import com.springboot.dao.WheelDao;
import com.springboot.domain.WheelInfo;
import com.springboot.qrcode.QRCodeUtil;
import com.springboot.service.WheelRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static java.time.Duration.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Deprecated
class Springboot4ApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    private WheelDao wheelDao;
    @Autowired
    WheelRepository wheelRepository;
    Random random = new Random(47);
    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
    }
    //@Test
    public  void createQualifiedWheelRepository(){
        for (int i=0;i<10;i++){
            redisTemplate.opsForList().rightPush("qualifiedRepository",100);
        }
        for (int i=0;i<10;i++){
            String name = "qualified"+i;
            for (int j=0;j<100;j++){
                redisTemplate.opsForList().rightPush(name,"null");
            }
        }
    }
    //@Test
    public  void createDiscardWheelRepository(){
        for (int i=0;i<3;i++){
            redisTemplate.opsForList().rightPush("discardRepository",100);
        }
        for (int i=0;i<3;i++){
            String name = "discard"+i;
            for (int j=0;j<100;j++){
                redisTemplate.opsForList().rightPush(name,"null");
            }
        }
    }
    @Test
    public void testStoreWheel(){
        List<Integer> position = wheelRepository.putAxleInPosition("003");
        System.out.println(position.get(0));
        System.out.println(position.get(1));
    }

    @Test
    public void dateFormate(){
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-M-d");
        String now = dateFormater.format(new Date());
        String[] date = now.split("-");
        System.out.println(date[0]);
        System.out.println(date[1]);
        System.out.println(date[2]);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int ty = 0;
        int tm = 0;
        tm = (year-1990)*12+month-22*12+5;
        ty = 1990+tm/12;
        tm = tm%12;
        String res = "";
        if (tm<10) {
            res = ty+"-0"+tm +"-00";
        }else {
            res = ty+"-"+tm +"-00";
        }
        System.out.println(res);
    }

    public String generateAxledate(){
        String axlemadeDate = null;
        int y = 1998;
        int m = 1;
        int d = 1;
        y = y + random.nextInt(22);
        m = m + random.nextInt(11);
        d = d + random.nextInt(30);
        if (m<10){
            if (d<10){
                axlemadeDate = y+"-0"+m +"-0"+d;
            }else {
                axlemadeDate = y+"-0"+m +"-"+d;
            }
        }else {
            if (d<10){
                axlemadeDate = y+"-"+ m +"-0"+d;
            }else {
                axlemadeDate = y+"-"+ m +"-"+d;
            }
        }
        return axlemadeDate;
    }
    public String generatewheelDiam(){
        String axlemadeDate = null;
        int y = 780;
        y = y + random.nextInt(40);
        axlemadeDate = y + "";
        return axlemadeDate;
    }
    @Test
    public void testgendate(){
        for (int i = 0;i<20;i++){
           // System.out.println(generateAxledate());
            System.out.println(generatewheelDiam());
        }
    }

    @Test
    public void split(){
        String str = "/userManage/getusersLog=2020-09-17 14:02:13=0:0:0:0:0:0:0:1";
        String[] arr = str.split("=");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);

    }
    @Test
    public void testHash(){
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        System.out.println(list.get(1));
    }
    @Test
    public void testTime() throws ParseException {
        String t1 = "2020-09-21 08:58:18";
        String t2 = "2020-09-21 10:48:38";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = dateFormat.parse(t1);
        Date date2= dateFormat.parse(t2);
        Long date = (date2.getTime()-date1.getTime())/1000;
        int hour = (int) (date/3600);
        int minute = (int) (date%3600/60);
        int second = (int) (date%3600%60);
        System.out.println(hour+":"+minute+":"+second);
    }
    @Test
    public void testQrcode() throws Exception {
        // 存放在二维码中的内容
        String text = "我是二维码";
        // 嵌入二维码的图片路径
        // String imgPath = "I:/qrCode/dog.jpg";
        // 生成的二维码的路径及名称
        String destPath = "I:/jam.jpg";
        //生成二维码
        QRCodeUtil.encode(text,null, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);

    }
    @Test
    public void testquery(){
        List<WheelInfo> data = null;
        data = wheelDao.findWheelInfoByCondition("",
                "",
                null,
                "",
                "4567128",
                null,
                null,
                null,
                null,
                "",
                "",
                "",
                "",
                "",
                //车轴制造单位
                "",
                //车轮制造单位
                "");
        System.out.println(data);
    }
    @Test
    void testSetMeasure(){
//        wheelDao.setWheelInfoWheelRoundingFinish(598);
        WheelInfo wheelInfo = wheelDao.findWheelInfoById(600);
        System.out.println(wheelInfo);
    }
    @Test
    void findPos(){
        Map<String,Integer> position = wheelRepository.findPosition("585","qualified");
        System.out.println(position);
    }
}


