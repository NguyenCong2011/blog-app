package com.example.blog_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
//@Table(name = "comment", uniqueConstraints = {
//        @UniqueConstraint(name = "id",columnNames = "id"),
//        @UniqueConstraint(name = "email",columnNames = "email")
//})
@Table(name = "comment")
public class Comment extends BaseEntity{
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Tên không được để trống")
  @Column(name = "name", length = 50 , nullable = false)
  private String name;

  @Email(message = "Email không hợp lệ")
  @Column(name = "email", length = 75,nullable = false)
  private String email;

  @NotBlank(message = "Nội dung không được để trống")
  @Column(name = "body", length = 100)
  private String body;

  @ManyToOne
  @JoinColumn(name = "post_id" ,
          referencedColumnName = "id",
//          unique = true, // cau hinh one to one
          nullable = false)

//  day la xoa bo la xoa luon ca con
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Post post;
}
