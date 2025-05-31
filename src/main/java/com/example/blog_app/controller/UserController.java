package com.example.blog_app.controller;

import com.example.blog_app.dto.request.UserDto;
import com.example.blog_app.form.user.UserCreateForm;
import com.example.blog_app.service.UserService.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class UserController {

  private UserService userService;

//  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/api/v1/auth/register")
  public UserDto createUser(@RequestBody @Valid UserCreateForm userCreateForm){
    return userService.createUser(userCreateForm);
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER')") // Chỉ cho phép người dùng có vai trò USER
  public String userEndpoint() {
    return "User access granted!";
  }
}
