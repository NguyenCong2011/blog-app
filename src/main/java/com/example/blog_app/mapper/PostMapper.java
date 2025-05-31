package com.example.blog_app.mapper;

import com.example.blog_app.dto.request.PostDto;
import com.example.blog_app.entity.Post;
import com.example.blog_app.form.Post.PostCreateForm;
import com.example.blog_app.form.Post.PostUpdateForm;

public class PostMapper {
  public static PostDto map(Post post){
    var dto = new PostDto();
    dto.setId(post.getId());
    dto.setTitle(post.getTitle());
    dto.setContent(post.getContent());
    dto.setDescription(post.getDescription());
    dto.setCreatedAt(post.getCreatedAt());
    dto.setUpdatedAt(post.getUpdatedAt());
    return dto;
  }

  public static Post map(PostCreateForm form){
    var post =new Post();
    post.setTitle(form.getTitle());
    post.setDescription(form.getDescription());
    post.setContent(form.getContent());
    return post;
  }
// anh xa cach thong tin ma nguoi dung muon cap nhap vao trong bai viet da co trong database
  public static void map(PostUpdateForm form,Post post){
      post.setTitle(form.getTitle());
      post.setDescription(form.getDescription());
      post.setContent(form.getContent());
  }
}
