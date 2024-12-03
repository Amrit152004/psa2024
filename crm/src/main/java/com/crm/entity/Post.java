package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    @Id

    private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;
  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = false)
  private Set<Comment> comments;

}