package com.example.blog_app.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PostDto {
  private Long id;
  private String title;
  private String description;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
