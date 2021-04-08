package com.springboot.domain;

import java.io.Serializable;
import java.util.List;

public class Blog implements Serializable {
    private Integer id;
    private Integer father;
    private String publisher;
    private String publishTime;
    private String content;
    private String replyto;
    private Integer like;
    private Integer dislike;
    private Integer commentnum;
    private List<Blog> comments;
    private String isShowComt;

    public Blog() {
        like = 0;
        dislike = 0;
        commentnum = 0;
        father = -1;
        isShowComt = "0";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFather() {
        return father;
    }

    public void setFather(Integer father) {
        this.father = father;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public List<Blog> getComments() {
        return comments;
    }

    public void setComments(List<Blog> comments) {
        this.comments = comments;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    public String getIsShowComt() {
        return isShowComt;
    }

    public void setIsShowComt(String isShowComt) {
        this.isShowComt = isShowComt;
    }

    public String getReplyto() {
        return replyto;
    }

    public void setReplyto(String replyto) {
        this.replyto = replyto;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", publisher='" + publisher + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", content='" + content + '\'' +
                ", like=" + like +
                ", dislike=" + dislike +
                ", comments=" + comments +
                '}';
    }
}
