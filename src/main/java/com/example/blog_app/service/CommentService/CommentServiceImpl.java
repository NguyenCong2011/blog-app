package com.example.blog_app.service.CommentService;

import com.example.blog_app.dto.request.CommentDto;
import com.example.blog_app.entity.Comment;
import com.example.blog_app.exceptions.PostNotFoundException;
import com.example.blog_app.form.Comment.CommentCreateForm;
import com.example.blog_app.form.Comment.CommentUpdateForm;
import com.example.blog_app.form.Filter.CommentFilterForm;
import com.example.blog_app.form.Post.PostCreateForm;
import com.example.blog_app.mapper.CommentMapper;
import com.example.blog_app.mapper.PostMapper;
import com.example.blog_app.repository.CommentRepository;
import com.example.blog_app.repository.PostRepository;
import com.example.blog_app.specification.CommentSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
  private CommentRepository commentRepository;
  private PostRepository postRepository;

  //  public List<PostDto> findAll(){
//    var posts = postRepository.findAll();
//    var dtos = new ArrayList<PostDto>();
//    for (Post post : posts) {
//      var dto = PostMapper.map(post);
//      dtos.add(dto);
//    }
//    return dtos;
//  }

  @Override
  public Page<CommentDto> findAll(CommentFilterForm commentFilterForm, Pageable pageable){
    var spec = CommentSpecification.buildSpec(commentFilterForm);
    return commentRepository.findAll(spec,pageable)
            .map(CommentMapper::map);
  }

  @Override
  public Page<CommentDto> findByPostId(Long postId, Pageable pageable) {
    var post =postRepository.findById(postId)
            .orElseThrow(()-> new PostNotFoundException("Khong tim thay bai viet co Id" + postId));
//    truy van danh sach comment theo postId voi phan trang
//    Page<Comment> comments = commentRepository.findByPostId(postId,pageable);
//    return comments.map(CommentMapper::map);
    return commentRepository.findByPostId(postId,pageable).map(CommentMapper::map);
  }

  @Override
  public CommentDto findById(Long id){
    return commentRepository.findById(id)
            .map(CommentMapper::map)
            .orElse(null);
  }


  @Override
  public CommentDto create(CommentCreateForm form, Long postId){
    var optional = postRepository.findById(postId);
    if(optional.isEmpty()){
      return null;
    }
    var post = optional.get();
    var comment = CommentMapper.map(form);
    comment.setPost(post);
    var saveComment = commentRepository.save(comment);
    return CommentMapper.map(saveComment);
  }
//  @Override
//  public CommentDto create(CommentCreateForm form, Long postId){
//    var post = postRepository.findById(postId)
//            .orElseThrow(()-> new PostNotFoundException("Không tìm thấy bài viết có ID " + postId));
//    var comment = CommentMapper.map(form);
//    comment.setPost(post);
//    var saveComment = commentRepository.save(comment);
//    return CommentMapper.map(saveComment);
//  }

  @Override
  public CommentDto updateComment(CommentUpdateForm form, Long id){
    var optional = commentRepository.findById(id);
    if(optional.isEmpty()){
      return null;
    }
    var comment = optional.get();
//    tham so thu nhat la form tham so thu 2 la 1 entity da co trong database
//    PostMapper.map(form, post):
//
//Gọi phương thức map của lớp PostMapper, thực hiện ánh xạ (mapping) dữ liệu từ PostUpdateForm sang đối tượng Post
// đã tồn tại trong cơ sở dữ liệu.
//Tham số thứ nhất: form, chứa dữ liệu mới cần cập nhật.
//Tham số thứ hai: post, bài viết cũ được cập nhật bằng dữ liệu từ form
    CommentMapper.map(form,comment);
    var saveComment = commentRepository.save(comment);
    return CommentMapper.map(saveComment);
  }


  @Override
  public void deleteById(Long id){
    commentRepository.deleteById(id);
  }
//xoa tat ca cac binh luan cua mot bai viet Post

  @Transactional
  @Override
  public void deleteAllByPostId(Long postId) {
    // Kiểm tra xem bài viết có tồn tại không
    if(!postRepository.existsById(postId)){
      throw new EntityNotFoundException("Khong tim thay bai viet co Id la"+
              postId+ "Khong ton tai");
    }
    // Kiểm tra xem có bình luận nào không
    if(!commentRepository.existsByPostId(postId)){
        throw new EntityNotFoundException(
                "Khong tim thay binh luan nao cho bai viet " + postId);
    }
    commentRepository.deleteAllByPostId(postId);
  }
}
