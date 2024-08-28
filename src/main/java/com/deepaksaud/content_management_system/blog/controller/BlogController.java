package com.deepaksaud.content_management_system.blog.controller;

import com.deepaksaud.content_management_system.blog.model.Blog;
import com.deepaksaud.content_management_system.blog.service.BlogService;
import com.deepaksaud.content_management_system.category.service.CategoryService;
import com.deepaksaud.content_management_system.tag.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    private final TagService tagService;

    public BlogController(BlogService blogService, CategoryService categoryService, TagService tagService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @GetMapping("/create")
    public String createBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("tags", tagService.getAllTags());
        return "blogs/blog-form";
    }

    @PostMapping
    public String saveBlog(
            @ModelAttribute Blog blog,
            @RequestParam Long categoryId,
            @RequestParam(required = false) Set<Long> tagIds,
            Model model) {

        if (categoryId == null) {
            model.addAttribute("error", "Category must be selected.");
            return createBlogForm(model);
        }

        if (tagIds == null || tagIds.isEmpty()) {
            model.addAttribute("error", "Please select at least one tag.");
            return createBlogForm(model);
        }

        try {
            blogService.createBlog(blog, categoryId, tagIds);
            return "redirect:/api/v1/blog";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return createBlogForm(model);
        }
    }


    @GetMapping
    public String listBlogs(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "blogs/blog-list";
    }

    @GetMapping("/{id}")
    public String viewBlogDetails(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid blog ID:" + id));
        model.addAttribute("blog", blog);
        return "blogs/blog-details";
    }

    @GetMapping("/edit/{id}")
    public String editBlogForm(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id).orElseThrow(() -> new IllegalArgumentException("Invalid blog ID:" + id));
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("tags", tagService.getAllTags());
        return "blogs/blog-form";
    }

    @PostMapping("/update")
    public String updateBlog(
            @ModelAttribute Blog blog,
            @RequestParam Long categoryId,
            @RequestParam Set<Long> tagIds) {
        blogService.createBlog(blog, categoryId, tagIds);
        return "redirect:/api/v1/blog";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/api/v1/blog";
    }
}
