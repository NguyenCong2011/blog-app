package com.example.blog_app.service.CommentService;

import com.example.blog_app.dto.request.CommentDto;
import com.example.blog_app.entity.Post;
import com.example.blog_app.form.Comment.CommentCreateForm;
import com.example.blog_app.form.Comment.CommentUpdateForm;
import com.example.blog_app.form.Filter.CommentFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
  Page<CommentDto> findAll(CommentFilterForm commentFilterForm, Pageable pageable);

//  lay ra tat ca cac comment cua 1 bai viet
  Page<CommentDto> findByPostId(Long postId, Pageable pageable);

  CommentDto findById(Long id);

  CommentDto create(CommentCreateForm form, Long postId);

  CommentDto updateComment(CommentUpdateForm form, Long id);

  void deleteById(Long id);

  void deleteAllByPostId(Long postId);
}
