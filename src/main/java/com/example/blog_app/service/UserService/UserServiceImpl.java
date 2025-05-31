package com.example.blog_app.service.UserService;


import com.example.blog_app.dto.request.UserDto;
import com.example.blog_app.entity.User;
import com.example.blog_app.form.user.UserCreateForm;
import com.example.blog_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService , UserDetailsService {
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private ModelMapper modelMapper;

  public UserDto createUser(UserCreateForm userCreateForm) {
    var user = modelMapper.map(userCreateForm, User.class);

    var encodedPassword = passwordEncoder.encode(userCreateForm.getPassword());
//     Lưu mật khẩu vào entity User
    user.setPassword(encodedPassword);

    var saveUser = userRepository.save(user);
    return modelMapper.map(saveUser,UserDto.class);
  }

  //  lấy ra thông tin người dùng tu tk người dùng
  @Override
  public UserDetails loadUserByUsername(String username)
          throws UsernameNotFoundException {

//    Gọi userRepository.findByUsernameOrEmail(username, username) để tìm user.
    var user = userRepository.findByUserNameOrEmail(username,username);
//     Nếu không tìm thấy, Spring Security sẽ trả về lỗi 401 Unauthorized.
    if(user == null){
      throw new UsernameNotFoundException("Khong tim thay nguoi dung "+ username);
    }
//    Lấy role của user (Ví dụ: "ADMIN").
//Chuyển role thành danh sách quyền hạn (GrantedAuthority).
    var role = user.getRole().name();  //ADMIN
    // Lấy vai trò của người dùng và chuyển thành danh sách quyền hạn
//    var authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
      var authorities = AuthorityUtils.createAuthorityList(role);
    return new org.springframework.security.core.userdetails.User(
            username,
            user.getPassword(),
            authorities);
  }
}