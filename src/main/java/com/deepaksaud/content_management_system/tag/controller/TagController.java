package com.deepaksaud.content_management_system.tag.controller;
import com.deepaksaud.content_management_system.tag.model.Tag;
import com.deepaksaud.content_management_system.tag.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // MVC: List tags
    @GetMapping
    public String listTags(Model model) {
        List<Tag> tags = tagService.getAllTags();
        model.addAttribute("tags", tags);
        return "tags";
    }

    // MVC: Edit a tag
    @GetMapping("/edit/{id}")
    public String editTagForm(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);
        return "tag-form";
    }

    // MVC: Update the tag
    @PostMapping("/update/{id}")
    public String updateTag(@PathVariable Long id, @ModelAttribute("tag") Tag tag) {
        tagService.updateTag(id, tag);
        return "redirect:/tags";
    }


}
