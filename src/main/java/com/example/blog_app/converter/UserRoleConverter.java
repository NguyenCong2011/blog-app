package com.example.blog_app.converter;

import com.example.blog_app.entity.User;
import jakarta.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<User.Role, Character> {
  @Override
  public Character convertToDatabaseColumn(User.Role role) {
//    convert tu role thanh 1 ki tu co trong database
    return role.toString().charAt(0);
  }

  @Override
  public User.Role convertToEntityAttribute(Character code) {
//    convert 1 ki tu trong database thanh 1 kieu enum trong role
    if(code == 'A'){
      return User.Role.ADMIN;
    }
    else if(code == 'E'){
      return User.Role.EMPLOYEE;
    }
    return User.Role.MANAGER;
  }
}
