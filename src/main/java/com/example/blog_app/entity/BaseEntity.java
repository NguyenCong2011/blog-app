package com.example.blog_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
  @Column(name = "created_at", nullable = false , updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "update_at", nullable = false)
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
