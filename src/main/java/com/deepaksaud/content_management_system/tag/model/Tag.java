package com.deepaksaud.content_management_system.tag.model;

import com.deepaksaud.content_management_system.blog.model.Blog;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String name;
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private Set<Blog> blogs;
}
