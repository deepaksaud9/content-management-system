package com.deepaksaud.content_management_system.blog.service;

import com.deepaksaud.content_management_system.blog.model.Blog;

import java.util.Set;

public interface BlogService {

    Blog createBlog(Blog blog, Long categoryId, Set<Long> tagIds);
}
