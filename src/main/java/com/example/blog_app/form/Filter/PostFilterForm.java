package com.example.blog_app.form.Filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostFilterForm {
  private String search;
  private LocalDate minCreatedDate;
  private LocalDate maxCreatedDate;
}
