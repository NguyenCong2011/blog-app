package com.example.blog_app.service.UserService;

import com.example.blog_app.dto.request.UserDto;
import com.example.blog_app.form.user.UserCreateForm;

public interface UserService {
  UserDto createUser(UserCreateForm userCreateForm);
}
