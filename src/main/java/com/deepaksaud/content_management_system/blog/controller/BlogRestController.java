package com.deepaksaud.content_management_system.blog.controller;

import com.deepaksaud.content_management_system.blog.model.Blog;
import com.deepaksaud.content_management_system.blog.service.BlogService;
import com.deepaksaud.content_management_system.category.service.CategoryService;
import com.deepaksaud.content_management_system.tag.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogRestController {

    private final BlogService blogService;
    private final CategoryService categoryService;
    private final TagService tagService;

    public BlogRestController(BlogService blogService, CategoryService categoryService, TagService tagService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }
        /*---------------------------------------REST APIS------------------------------*/

    // REST: Get Blog by ID (JSON Response)
    @GetMapping("/rest/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        return blog.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // REST: Get All Blogs (JSON Response)
    @GetMapping("/rest/fetchAllBlog")
    public ResponseEntity<List<Blog>> fetchAllBlogs() {
        List<Blog> blogs = (List<Blog>) blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    // REST: Create Blog (JSON Request and Response)
    @PostMapping("/rest/save")
    public ResponseEntity<Blog> createBlogRest(
            @RequestBody Blog blog,
            @RequestParam Long categoryId,
            @RequestParam Set<Long> tagIds) {
        try {
            Blog createdBlog = blogService.createBlog(blog, categoryId, tagIds);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBlog);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Update an existing blog
    @PutMapping("/rest/update/{id}")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable Long id,
            @RequestBody Blog blog,
            @RequestParam Long categoryId,
            @RequestParam List<Long> tagIds) { // Change Set<Long> to List<Long>
        Optional<Blog> existingBlogOpt = blogService.getBlogById(id);
        if (existingBlogOpt.isPresent()) {
            Blog existingBlog = existingBlogOpt.get();
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent(blog.getContent());

            try {
                // Convert List<Long> to Set<Long>
                Set<Long> tagSet = new HashSet<>(tagIds);
                Blog updatedBlog = blogService.createBlog(existingBlog, categoryId, tagSet);
                return ResponseEntity.ok(updatedBlog);
            } catch (IllegalArgumentException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/rest/delete/{id}")
    public ResponseEntity<String> deleteBlogById(@PathVariable Long id) {
        try {
            blogService.deleteBlog(id);
            return new ResponseEntity<>("Blog deleted successfully", HttpStatus.OK);
        } catch (Exception ex) {
            // Handle exceptions (e.g., Blog not found)
            return new ResponseEntity<>("Blog not found", HttpStatus.NOT_FOUND);
        }
    }
}
