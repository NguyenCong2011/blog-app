package com.example.blog_app.mapper;

import com.example.blog_app.dto.request.CommentDto;
import com.example.blog_app.entity.Comment;
import com.example.blog_app.form.Comment.CommentCreateForm;
import com.example.blog_app.form.Comment.CommentUpdateForm;

public class CommentMapper {
  public static CommentDto map(Comment comment){
    var dto = new CommentDto();
    dto.setId(comment.getId());
    dto.setName(comment.getName());
    dto.setEmail(comment.getEmail());
    dto.setBody(comment.getBody());
    dto.setCreatedAt(comment.getCreatedAt());
    dto.setUpdatedAt(comment.getUpdatedAt());
    return dto;
  }

  public static Comment map(CommentCreateForm commentCreateForm){
    var comment = new Comment();
    comment.setName(commentCreateForm.getName());
    comment.setEmail(commentCreateForm.getEmail());
    comment.setBody(commentCreateForm.getBody());
    return comment;
  }

  public static void map(CommentUpdateForm form, Comment comment){
    comment.setName(form.getName());
    comment.setEmail(form.getEmail());
    comment.setBody(form.getBody());
  }
}
