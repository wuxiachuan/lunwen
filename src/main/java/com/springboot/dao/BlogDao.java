package com.springboot.dao;

import com.springboot.domain.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogDao {
    void insertBlog(Blog blog);
    void deleteBlog(Integer id);
    void deleteBlogComment(Integer id);
    Blog findBlogById(Integer id);
    List<Blog> findBlog(String name);
    List<Blog> findAllBlogs();
    List<Blog> findCommentBlog(Integer id);
}
