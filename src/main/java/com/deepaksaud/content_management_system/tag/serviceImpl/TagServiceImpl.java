package com.deepaksaud.content_management_system.tag.serviceImpl;

import com.deepaksaud.content_management_system.tag.model.Tag;
import com.deepaksaud.content_management_system.tag.repository.TagRepository;
import com.deepaksaud.content_management_system.tag.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {


    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new RuntimeException("tag not found"));
    }

    public Tag updateTag(Long id, Tag updateTag) {
        Tag tag = getTagById(id);
        tag.setName(updateTag.getName());
        return tagRepository.save(tag);
    }

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
