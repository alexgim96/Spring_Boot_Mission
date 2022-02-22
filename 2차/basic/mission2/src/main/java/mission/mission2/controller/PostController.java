package mission.mission2.controller;

import mission.mission2.entity.Board;
import mission.mission2.entity.Post;
import mission.mission2.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final Service<Post> postService;
    private final Service<Board> boardService;


    public PostController(@Autowired Service<Post> postService, @Autowired Service<Board> boardService) {
        this.postService = postService;
        this.boardService = boardService;
    }

    @GetMapping("/writePre/{id}")
    public ModelAndView writePre(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        logger.info(String.format("%d", id));
        modelAndView.addObject("id", id);
        modelAndView.setViewName("postWrite");
        return modelAndView;
    }

    @PostMapping("/write")
    public ModelAndView wirte(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("author") String author,
                              @RequestParam("pwd") String pwd,
                              @RequestParam("boardId") int id){
        logger.info("write process");
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post(id, title, content, author, pwd);
        logger.info("postId1 : " + String.valueOf(post.getId()));
        this.postService.createItem(post);
        logger.info("boardid : " + String.valueOf(id));
        logger.info("postId2 : " + String.valueOf(post.getId()));
        String url = "redirect:/board/view/";
        url += String.valueOf(id);
        logger.info(url);
        modelAndView.setViewName(url);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Post post = this.postService.viewOne(id-1);
        modelAndView.addObject("post", post);
        modelAndView.setViewName("postDetail");
        return modelAndView;
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", this.postService.viewOne(id-1));
        modelAndView.setViewName("postModify");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int id,
                               @RequestParam(name = "title") String title,
                               @RequestParam(name = "content") String content,
                               @RequestParam(name = "author") String author,
                               @RequestParam(name = "pwd") String pwd,
                               @RequestParam(name = "boardId") int boardId){
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        post.setPwd(pwd);
        post.setBoardId(boardId);
        this.postService.modifyItem(id-1, post);
        String url = "redirect:/board/view/";
        url += String.valueOf(post.getBoardId());
        logger.info("url : " + url);
        modelAndView.setViewName(url);
        return modelAndView;
    }

    @GetMapping("/deletePre/{id}")
    public ModelAndView deletePre(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Post post = this.postService.viewOne(id-1);
        modelAndView.addObject("post", post);
        modelAndView.setViewName("deleteCheck");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id, @RequestParam("pwd") String pwd){
        ModelAndView modelAndView = new ModelAndView();
        logger.info("pwd : " + pwd + " 게시물의 pwd : " + this.postService.viewOne(id-1).getPwd());
        if(pwd.equals(this.postService.viewOne(id-1).getPwd())) {
            this.postService.deleteItem(id - 1);
            String url = "redirect:/board/view/";
            url += String.valueOf(id);
            modelAndView.setViewName(url);
            return modelAndView;
        }else{
            modelAndView.addObject("post", this.postService.viewOne(id-1));
            modelAndView.setViewName("reDeleteCheck");
            return modelAndView;
        }
    }
}
