package com.deepaksaud.content_management_system.category.controller;

import com.deepaksaud.content_management_system.category.model.Category;
import com.deepaksaud.content_management_system.category.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @PostMapping
    public String addCategory(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    // MVC: List categories
    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories"; // returns categories.html view
    }
}
