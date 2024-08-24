package com.deepaksaud.content_management_system.category.service;

import com.deepaksaud.content_management_system.category.model.Category;

import java.util.List;

public interface CategoryService {

    public Category addCategory(Category category);

    public List<Category> getAllCategories();

    public Category getCategoryById(Long id);

    public Category updateCategory(Long id, Category updatedCategory);

    public void deleteCategory(Long id);
}
