package com.deepaksaud.content_management_system.tag.serviceImpl;

import com.deepaksaud.content_management_system.tag.model.Tag;
import com.deepaksaud.content_management_system.tag.repository.TagRepository;
import com.deepaksaud.content_management_system.tag.service.TagService;
import org.springframework.stereotype.Service;

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
}
