package com.example.blog_app.controller;

import com.example.blog_app.dto.reponse.ResponseData;
import com.example.blog_app.dto.request.CommentDto;
import com.example.blog_app.form.Comment.CommentCreateForm;
import com.example.blog_app.form.Comment.CommentUpdateForm;
import com.example.blog_app.form.Filter.CommentFilterForm;
import com.example.blog_app.service.CommentService.CommentService;
import com.example.blog_app.validation.CommentIdExists.CommentIdExist;
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
@Validated
public class CommentController {

  private CommentService commentService;


  @GetMapping("api/v1/comment")
  Page<CommentDto> findAll(CommentFilterForm commentFilterForm, Pageable pageable){
    return commentService.findAll(commentFilterForm,pageable);
  }

//  lay ra tat ca cac comment tren 1 bai viet
  @GetMapping("api/v1/comment/posts/{postId}/comments")
  public Page<CommentDto> findByPostId(
          @PathVariable @PostIdExists Long postId,
          Pageable pageable){
    return commentService.findByPostId(postId,pageable);
  }
//lay ra comment
  @GetMapping("api/v1/comment/{id}")
  public CommentDto findById(
          @PathVariable("id") @CommentIdExist Long id
  ){
    return commentService.findById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("api/v1/posts/{postId}/comments")
  public CommentDto create(@RequestBody @Valid  CommentCreateForm commentCreateForm,
                           @PathVariable("postId") @PostIdExists Long postId){
    return commentService.create(commentCreateForm,postId);
  }


  @PutMapping("api/v1/comment/{id}")
  public CommentDto update(@RequestBody @Valid CommentUpdateForm commentUpdateForm,
                        @PathVariable @CommentIdExist Long id){
    return commentService.updateComment(commentUpdateForm,id);
  }


  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("api/v1/comment/{id}")
  public ResponseEntity<ResponseData<String>> deleteById(@PathVariable @CommentIdExist  Long id) {
    commentService.deleteById(id);
    ResponseData<String> responseData = new ResponseData<>(
            HttpStatus.OK.value(),
            "Comment deleted"
    );
    return ResponseEntity.ok(responseData);
  }


  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("api/v1/post/{postId}/comments")
  public void deleteAllByPostId(
          @PathVariable @PostIdExists Long postId
  ){
    commentService.deleteAllByPostId(postId);
  }
}
