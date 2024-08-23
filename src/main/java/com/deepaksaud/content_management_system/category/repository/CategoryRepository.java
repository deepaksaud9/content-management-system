package com.deepaksaud.content_management_system.category.repository;

import com.deepaksaud.content_management_system.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
