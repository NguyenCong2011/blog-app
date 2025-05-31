package com.example.blog_app.form.Filter;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentFilterForm {
  private String search;
  private Long postId;
}
