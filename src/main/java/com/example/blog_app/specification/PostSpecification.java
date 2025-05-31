package com.example.blog_app.specification;

import com.example.blog_app.entity.Post;
import com.example.blog_app.form.Filter.PostFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class PostSpecification {
//  PostSpecification la mot menh de where

//  Specification<Post> dau ra la 1 menh de where
  public static Specification<Post> buildWhere(PostFilterForm form){
    return form == null ? null : new Specification<Post>() {
//Root<Post> root: Đại diện cho thực thể Post, giúp truy cập các thuộc tính (title, content, author, v.v.).
//CriteriaQuery<?> query: Đại diện cho truy vấn tổng thể, giúp tùy chỉnh truy vấn.
//CriteriaBuilder builder: Công cụ giúp xây dựng các điều kiện (Predicate) linh hoạt.
      @Override
      public Predicate toPredicate(
              Root<Post> root,
              CriteriaQuery<?> query,
              CriteriaBuilder builder
      ) {
        if(form ==null){
//          builder.conjunction():
//          Trả về một điều kiện mặc định đúng (tương đương với WHERE 1=1),
//          giúp tránh lỗi khi không có điều kiện nào.
//          Nếu form là null,
//          phương thức trả về builder.conjunction(), tức là một điều kiện "vô hại" (true), không lọc gì cả.
          return builder.conjunction();
        }
//        tạo ra 1 list của điều kiện Danh sách predicates chứa các điều kiện lọc.
        var predicates = new ArrayList<Predicate>();

        String  search = form.getSearch();
        if(search != null && !search.trim().isEmpty()){
//          title LIKE "%..$
          var predicate = builder.like(
                  root.get("title"),"%" + search + "%"
          );
          predicates.add(predicate);
        }
// Lọc theo thời gian tạo (created_at)
        var minCreateDate = form.getMinCreatedDate();
//      2024-01-01 00:00:00
        if(minCreateDate != null){
          var minCreateAt = LocalDateTime.of(minCreateDate, LocalTime.MIN);
//          SELECT * FROM post WHERE created_at >= '2024-02-10 00:00:00';
          var predicate =  builder.greaterThanOrEqualTo(
                  root.get("createdAt"),
                  minCreateAt
          );
          predicates.add(predicate);
        }

        var maxCreateDate = form.getMaxCreatedDate();

        if(maxCreateDate != null){
          var maxCreateAt = LocalDateTime.of(maxCreateDate,LocalTime.MAX);
//          SELECT * FROM post WHERE created_at <= '?';
          var predicate = builder.lessThanOrEqualTo(
                  root.get("createdAt")
                  ,maxCreateAt
          );
          predicates.add(predicate);
        }
// Trả về điều kiện lọc kết hợp bằng AND
//        predicates.toArray(new Predicate[0]): Chuyển danh sách predicates thành mảng Predicate[].
//builder.and(...): Kết hợp tất cả điều kiện bằng phép AND.
        return builder.and(predicates.toArray(new Predicate[0]));
      };
    };
  }




//  Mục đích: Xây dựng điều kiện tìm kiếm động dựa trên PostFilterForm.
//Cơ chế hoạt động:
//Nếu form == null, trả về null.
//Nếu form không null, kiểm tra search:
//Nếu có giá trị, thêm điều kiện title LIKE '%search%' vào danh sách.
//Trả về danh sách điều kiện với AND.
//Ưu điểm: Giúp tạo truy vấn động, có thể mở rộng để thêm nhiều tiêu chí lọc khác (content, author, category, v.v.).

}
