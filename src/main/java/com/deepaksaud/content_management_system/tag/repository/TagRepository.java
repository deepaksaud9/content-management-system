package com.deepaksaud.content_management_system.tag.repository;

import com.deepaksaud.content_management_system.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {


}
