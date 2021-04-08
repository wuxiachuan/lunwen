package com.springboot.domain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class FileContent {
    private byte[] content;
    private String content_type;

    public byte[] getContent() {
       return content;
   }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public static FileContent getImageByte(String img){
        //String imgUrl="http://mmbiz.qpic.cn/mmbiz/yqVAqoZvDibEaicDyIvX7dLE9icYnwb2tlOtSzGMCglvqk61JjpW338xMSlJsKPVBiaD6FY1M5MtopJYvWrVYeYwFA/0?wx_fmt=jpeg";
        ByteArrayOutputStream outStream = null;
        FileContent file=new FileContent();
        String contentType="";
        try {
            URL url=new URL(img);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);
            contentType=conn.getHeaderField("Content-Type");
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();

            outStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len=inStream.read(buffer)) != -1 ){
                outStream.write(buffer, 0, len);
            }
            ((InputStream) inStream).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] content= outStream.toByteArray();
        file.setContent(content);
        file.setContent_type(contentType);

        return file;
    }
}
