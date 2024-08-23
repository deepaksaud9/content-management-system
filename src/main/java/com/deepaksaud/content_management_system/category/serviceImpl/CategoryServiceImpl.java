package com.deepaksaud.content_management_system.category.serviceImpl;

import com.deepaksaud.content_management_system.category.model.Category;
import com.deepaksaud.content_management_system.category.repository.CategoryRepository;
import com.deepaksaud.content_management_system.category.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category addCategory(Category category) {

        return categoryRepository.save(category);
    }
}
