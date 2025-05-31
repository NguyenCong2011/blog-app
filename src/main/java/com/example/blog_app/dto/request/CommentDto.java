package com.example.blog_app.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
  private Long id;

  private String name;

  private String email;

  private String body;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
