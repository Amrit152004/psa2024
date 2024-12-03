package com.crm.cotroller;

import com.crm.entity.Post;
import com.crm.repository.CommentRepository;
import com.crm.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostRepository postRepository;
    private CommentRepository commentRepository;


    public PostController(PostRepository postRepository  ,CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
    @PostMapping
    public String  craetePost(
            @RequestBody Post post
    ){
        postRepository.save(post);
        return "saved successfully";
    }
    @DeleteMapping
    public void deletePost(){
        postRepository.deleteById(1L);
    }
}
