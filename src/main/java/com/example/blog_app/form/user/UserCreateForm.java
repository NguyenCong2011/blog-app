package com.example.blog_app.form.user;

import com.example.blog_app.validation.Passwordcheck.PasswordValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserCreateForm {
  @NotBlank(message = "Tên không được để trống ")
  @Length(max = 50,message = "Tên có tối đa 50 kí tự")
  private String name;

  @NotBlank(message = "Username không được để trống")
  @Length(max = 100,message = "Username có tối đa 100 kí tự")
  private String userName;

  @Email
  @NotBlank(message = "Email không được để trống")
  @Length(max = 50,message = "Email có tối đa 50 kí tự")
  private String email;

  @NotBlank(message = "Mật khẩu không được để trống")
  @PasswordValid
  @Length(min = 8, max = 32,message = "Mật khẩu phải có từ 8 đến 32 ký tự")
  private String password;

  @NotBlank(message = "Role không được trống")
  @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER")
  private String role;
}
