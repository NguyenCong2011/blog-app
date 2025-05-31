package com.example.blog_app.controller;

import com.example.blog_app.dto.request.PostDto;
import com.example.blog_app.dto.reponse.ResponseData;
import com.example.blog_app.form.Filter.PostFilterForm;
import com.example.blog_app.form.Post.PostCreateForm;
import com.example.blog_app.form.Post.PostUpdateForm;
import com.example.blog_app.service.PostService.PostService;
import com.example.blog_app.validation.PostIdExists.PostIdExists;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
//@CrossOrigin("https://www.google.com/")
@CrossOrigin("*")
@Validated
public class PostController {
  private PostService postService;

  @GetMapping("api/v1/posts")
  public Page<PostDto> findAll(PostFilterForm form, Pageable pageable){
    return postService.findAll(form,pageable);
  }

  @GetMapping("api/v1/posts/{id}")
  public PostDto findById(@PathVariable("id") @PostIdExists Long id){

    return postService.findById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("api/v1/posts")
  public PostDto create(@RequestBody @Valid PostCreateForm form){
    return postService.create(form);

  }

  @PutMapping("api/v1/posts/{id}")
  public PostDto update(@RequestBody @Valid PostUpdateForm form,
                        @PathVariable @PostIdExists Long id){
    return postService.update(form,id);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("api/v1/posts/{id}")
  public ResponseEntity<ResponseData<String>> deleteById(@PathVariable @PostIdExists Long id) {
    postService.deleteById(id);
    ResponseData<String> responseData = new ResponseData<>(
            HttpStatus.OK.value(),
            "User deleted"
    );
    return ResponseEntity.ok(responseData);
  }

}
