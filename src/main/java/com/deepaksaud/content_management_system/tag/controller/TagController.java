package com.deepaksaud.content_management_system.tag.controller;

import com.deepaksaud.content_management_system.tag.model.Tag;
import com.deepaksaud.content_management_system.tag.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    // MVC: Create a new tag
    @GetMapping("/create")
    public String createTagForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "tag-form";
    }

    // MVC: Save the new tag
    @PostMapping
    public String saveTag(@ModelAttribute("tag") Tag tag) {
        tagService.createTag(tag);
        return "redirect:/tags";
    }


}
