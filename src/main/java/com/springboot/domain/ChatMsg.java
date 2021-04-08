package com.springboot.domain;

public class ChatMsg {
    private String from;
    private String to;
    private String content;
    private String date;
    private String fromNickname;

    public ChatMsg() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromNickname() {
        return fromNickname;
    }

    public void setFromNickname(String fromNickname) {
        this.fromNickname = fromNickname;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "ChatMsg{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", fromNickname='" + fromNickname + '\'' +
                '}';
    }
}
