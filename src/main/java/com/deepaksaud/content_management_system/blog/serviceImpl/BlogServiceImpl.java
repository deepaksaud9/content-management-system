package com.deepaksaud.content_management_system.blog.serviceImpl;

import com.deepaksaud.content_management_system.blog.model.Blog;
import com.deepaksaud.content_management_system.blog.repository.BlogRepository;
import com.deepaksaud.content_management_system.blog.service.BlogService;
import com.deepaksaud.content_management_system.category.model.Category;
import com.deepaksaud.content_management_system.category.repository.CategoryRepository;
import com.deepaksaud.content_management_system.tag.model.Tag;
import com.deepaksaud.content_management_system.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public BlogServiceImpl(BlogRepository blogRepository, CategoryRepository categoryRepository, TagRepository tagRepository){
        this.blogRepository = blogRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }


    @Override
    public Blog createBlog(Blog blog, Long categoryId, Set<Long> tagIds) {
        if (categoryId == null || tagIds == null) {
            throw new IllegalArgumentException("Category ID and Tag IDs must not be null");
        }

        // Set Category
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        if (categoryOpt.isPresent()) {
            blog.setCategory(categoryOpt.get());
        } else {
            throw new IllegalArgumentException("Category not found");
        }

        // Set Tags
        List<Tag> tagList = tagRepository.findAllById(tagIds);
        Set<Tag> tagSet = new HashSet<>(tagList);

        // Check if all tagIds are found
        if (tagSet.size() != tagIds.size()) {
            throw new IllegalArgumentException("Some tags not found");
        }
        blog.setTags(tagSet);

        // Save or update blog
        return blogRepository.save(blog);
    }

    @Override
    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Iterable<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }


}
