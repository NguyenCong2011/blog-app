package com.example.blog_app.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
  private Long id;
  private String name;
  private String userName;
  private String email;
  private String password;
  private String role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
