package com.example.blog_app.repository;

import com.example.blog_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
//  admin , employee, manager
//  / 1️⃣ Phương thức đếm số user theo role (admin, employee, manager)
    long countByRole(User.Role role);

//    SELECT * FROM users WHERE username = ? OR email = ? LIMIT 1;
    User findByUserNameOrEmail(String userName, String email);


}
