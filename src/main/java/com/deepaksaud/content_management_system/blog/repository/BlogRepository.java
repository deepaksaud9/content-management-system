package com.deepaksaud.content_management_system.blog.repository;

import com.deepaksaud.content_management_system.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Long, Blog> {
}
