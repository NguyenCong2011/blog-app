package com.example.blog_app.service.PostService;

import com.example.blog_app.dto.request.PostDto;
import com.example.blog_app.form.Filter.PostFilterForm;
import com.example.blog_app.form.Post.PostCreateForm;
import com.example.blog_app.form.Post.PostUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
  //  dung java 8
  Page<PostDto> findAll(PostFilterForm postFilterForm, Pageable pageable);

  PostDto findById(Long id);

  PostDto create(PostCreateForm form);

  PostDto update(PostUpdateForm form, Long id);

  void deleteById(Long id);
}
