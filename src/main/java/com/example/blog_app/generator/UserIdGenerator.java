package com.example.blog_app.generator;

import com.example.blog_app.entity.User;
import com.example.blog_app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

@AllArgsConstructor
public class UserIdGenerator implements IdentifierGenerator {
  private UserRepository userRepository;

  @Override
  public Object generate(SharedSessionContractImplementor session, Object object) {
    var user = (User) object;
    var role = user.getRole();
    long count = userRepository.countByRole(role);
    return String.format("%c-%d",role.toString().charAt(0), count+1);
  }
}
