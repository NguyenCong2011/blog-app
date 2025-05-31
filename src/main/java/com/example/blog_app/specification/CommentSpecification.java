package com.example.blog_app.specification;

import com.example.blog_app.entity.Comment;
import com.example.blog_app.form.Filter.CommentFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class CommentSpecification {
//  CommentSpecification
  public static Specification<Comment> buildSpec(CommentFilterForm commentFilterForm){
    return commentFilterForm == null ? null : new Specification<Comment>() {
      @Override
      public Predicate toPredicate(
              Root<Comment> root,
              CriteriaQuery<?> query,
              CriteriaBuilder builder
      ) {
        if(commentFilterForm == null){
          return builder.conjunction();
        }
//         khi bạn muốn tìm kiếm các bình luận có chứa một từ khóa trong
//         trường name hoặc userName
        var predicates = new ArrayList<Predicate>();
        String search = commentFilterForm.getSearch();
        if(search != null && !search.trim().isEmpty()){
//          WHERE name like "%...%" OR username LIKE "%...%"
          var hasNameLike = builder.like(
                  root.get("name"),"%"+search+"%"
          );

          var hasEmailLike = builder.like(
                  root.get("email"),"%"+search+"%"
          );

          var predicate = builder.or(hasNameLike,hasEmailLike);
          predicates.add(predicate);
        }

//        lay ra comment dua tren bai viet nao do
        var postId = commentFilterForm.getPostId();
        if(postId != null){
//          WHERE post_id =?
          var predicate = builder.equal(
                  root.get("post").get("id"),
                  postId
          );
          predicates.add(predicate);
        }

        // Trả về tất cả dữ liệu nếu không có điều kiện nào
        return predicates.isEmpty() ? builder.conjunction() : builder.and(predicates.toArray(new Predicate[0]));
      };
    };
  }
}
