package mission3.mission3.controller;

import mission3.mission3.dto.PostDto;
import mission3.mission3.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/{boardId}/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(@Autowired PostService postService){
        this.postService = postService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@PathVariable("boardId") int boardId, @RequestBody PostDto postDto){
        this.postService.createPost(boardId, postDto);
    }

    @GetMapping("/{id}")
    public PostDto readPost(@PathVariable("boardId") int boardId, @PathVariable("id") int id){
        return this.postService.readPost(boardId, id);
    }

    @GetMapping()
    public List<PostDto> readAllPost(@PathVariable("boardId") int boardId){
        return this.postService.readAllPost(boardId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePost(@PathVariable("id") int id, @RequestBody PostDto postDto){
        this.postService.updatePost(id, postDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePost(@PathVariable("id") int id){
        this.postService.deletePost(id);
    }
}
