package com.example.blog_app.entity;


import com.example.blog_app.generator.UserIdGenerator;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "post")
public class Post extends BaseEntity{
//  @SequenceGenerator(
//          name = "user_id_generation",
//          sequenceName = "user_id_sequence",
//          initialValue = 10,
//          allocationSize = 1
//
//  )
//  cach 2
//  @GeneratedValue(strategy = GenerationType.SEQUENCE)
//  @GeneratedValue(generator = "user_id_generation")

//  @GeneratedValue(strategy = GenerationType.UUID)
//  @JdbcType(value = CharJdbcType.class)
//  private UUID id;

//  @GenericGenerator(name="user_id_generator",
//          type = UserIdGenerator.class)
//  @GeneratedValue(generator = "user_id_generator")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title", length = 50, nullable = false)
  private String title;

  @Column(name = "description",length = 100, nullable = false)
  private String description;

  @Column(name = "content" , length = 150 , nullable = false)
  private String content;

  @OneToMany(mappedBy = "post")
  private List<Comment> comments;
}
