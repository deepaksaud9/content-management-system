package com.deepaksaud.content_management_system.tag.service;

import com.deepaksaud.content_management_system.tag.model.Tag;

import java.util.List;

public interface TagService {

    public Tag createTag(Tag tag);

    public List<Tag> getAllTags();
}
