package com.example.blog_app.service.PostService;


import com.example.blog_app.dto.request.PostDto;
import com.example.blog_app.entity.Post;
import com.example.blog_app.form.Filter.PostFilterForm;
import com.example.blog_app.form.Post.PostCreateForm;
import com.example.blog_app.form.Post.PostUpdateForm;
import com.example.blog_app.mapper.PostMapper;
import com.example.blog_app.repository.PostRepository;
import com.example.blog_app.specification.PostSpecification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
  private PostRepository postRepository;
  private ModelMapper modelMapper;

//  public List<PostDto> findAll(){
//    var posts = postRepository.findAll();
//    var dtos = new ArrayList<PostDto>();
//    for (Post post : posts) {
//      var dto = PostMapper.map(post);
//      dtos.add(dto);
//    }
//    return dtos;
//  }

//  dung java 8
@Override
public Page<PostDto> findAll(PostFilterForm postFilterForm, Pageable pageable){
  var spec = PostSpecification.buildWhere(postFilterForm);
    return postRepository.findAll(spec, pageable)
//  findAll(pageable) lấy danh sách bài viết theo kiểu Page<Post> với phân trang và sắp xếp.
//            .map(new Function<Post, PostDto>() {
//              @Override
//              public PostDto apply(Post post) {
//                return modelMapper.map(post,PostDto.class);
//              }
//            }
            .map(post -> modelMapper.map(post,PostDto.class));
  }

  @Override
  public PostDto findById(Long id){
    return postRepository.findById(id)
            .map(post -> modelMapper.map(post,PostDto.class))
            .orElse(null);
  }

  @Override
  public PostDto create(PostCreateForm form){
//    var post = PostMapper.map(form);
    var post = modelMapper.map(form,Post.class);
    var savePost = postRepository.save(post);
    return modelMapper.map(savePost,PostDto.class);
  }

  @Override
  public PostDto update(PostUpdateForm form, Long id){
    var optional = postRepository.findById(id);
    if(optional.isEmpty()){
      return null;
    }
    var post = optional.get();
//    tham so thu nhat la form tham so thu 2 la 1 entity da co trong database
//    PostMapper.map(form, post):
//
//Gọi phương thức map của lớp PostMapper, thực hiện ánh xạ (mapping) dữ liệu từ PostUpdateForm sang đối tượng Post
// đã tồn tại trong cơ sở dữ liệu.
//Tham số thứ nhất: form, chứa dữ liệu mới cần cập nhật.
//Tham số thứ hai: post, bài viết cũ được cập nhật bằng dữ liệu từ form
    modelMapper.map(form,post);
    var savePost = postRepository.save(post);
    return modelMapper.map(savePost,PostDto.class);

  }

  @Override
  public void deleteById(Long id){
    postRepository.deleteById(id);
  }

}
