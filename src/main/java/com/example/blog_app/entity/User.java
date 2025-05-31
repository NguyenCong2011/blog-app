package com.example.blog_app.entity;

import com.example.blog_app.converter.UserRoleConverter;
import com.example.blog_app.generator.UserIdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter

public class User extends BaseEntity{
//  cach 1: GenerationType.IDENTITY
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  cach 2: SEQUENCE
//  @SequenceGenerator(
//          name = "user_id_generation",
//          sequenceName = "user_id_sequence",
//          initialValue = 10,
//          allocationSize = 1
//
//  )

//  @GeneratedValue(generator = "user_id_generation")
//  cach 3 GenerationType.UUID

//  @GeneratedValue(strategy = GenerationType.AUTO)
//  @GeneratedValue(strategy = GenerationType.UUID)
//  cach 5 custom
//  @GenericGenerator(name="user_id_generator",
//  type = UserIdGenerator.class)
//  @GeneratedValue(generator = "user_id_generator")


  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name",length = 50, nullable = false)
  private String name;

  @Column(name = "username", length = 50, unique = true,nullable = false)
  private String userName;

  @Column(name = "email", length = 50, unique = true, nullable = false)
  private String email;

  @Column(name = "password",length = 100, nullable = false)
  private String password;

//  @Column(name = "role" , nullable = false)
////  c1: luu theo chi so
////  @Enumerated(EnumType.ORDINAL)
////  c2: luu theo ten String
////  @Enumerated(EnumType.STRING)
////  c3: Converter();
//  @Convert(converter = UserRoleConverter.class)
//  private Role role;

  @Column(name = "role", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Role role;

  public enum Role {
    ADMIN,EMPLOYEE,MANAGER
  }
}
