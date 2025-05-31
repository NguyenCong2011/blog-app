package com.example.blog_app.validation.CommentIdExists;

import com.example.blog_app.repository.CommentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentIdExistsValidator implements ConstraintValidator<CommentIdExist,Long> {
  private CommentRepository commentRepository;
  @Override
  public boolean isValid(Long id, ConstraintValidatorContext context) {
    return commentRepository.existsById(id) ;
  }
}
