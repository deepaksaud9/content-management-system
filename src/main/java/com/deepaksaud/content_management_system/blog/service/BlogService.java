package com.deepaksaud.content_management_system.blog.service;

import com.deepaksaud.content_management_system.blog.model.Blog;

import java.util.Optional;
import java.util.Set;

public interface BlogService {

    Blog createBlog(Blog blog, Long categoryId, Set<Long> tagIds);

    public Optional<Blog> getBlogById(Long id);

    public Iterable<Blog> getAllBlogs();

    public void deleteBlog(Long id);
}
