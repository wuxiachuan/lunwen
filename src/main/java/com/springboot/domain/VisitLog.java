package com.springboot.domain;

public class VisitLog {
      private Integer id;
      private String name;
      private String date;
      private String url;
      private String ip;

    public VisitLog(String name, String date, String url, String ip) {
        this.name = name;
        this.date = date;
        this.url = url;
        this.ip = ip;
    }

    public VisitLog() {
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "VisitLog{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
