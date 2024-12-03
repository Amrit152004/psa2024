package com.crm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
  @Id

  private Long id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "description", nullable = false, length = 500)
  private String description;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

  public void setPost(Post post) {
    this.post = post;

  }
}