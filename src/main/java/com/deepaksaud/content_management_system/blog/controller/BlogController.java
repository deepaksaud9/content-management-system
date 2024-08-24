package com.deepaksaud.content_management_system.blog.controller;

import com.deepaksaud.content_management_system.blog.model.Blog;
import com.deepaksaud.content_management_system.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @PostMapping
    public String saveBlog(
            @ModelAttribute Blog blog,
            @RequestParam Long categoryId,
            @RequestParam Set<Long> tagIds) {
        blogService.createBlog(blog, categoryId, tagIds);
        return "redirect:/blogs";
    }
    
}
