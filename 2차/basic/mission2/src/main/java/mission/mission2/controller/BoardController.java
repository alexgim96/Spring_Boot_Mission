package mission.mission2.controller;

import mission.mission2.entity.Board;
import mission.mission2.entity.Post;
import mission.mission2.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final Service<Board> service;
    private final Service<Post> postService;

    public BoardController(@Autowired Service<Board> service, @Autowired Service<Post> postService) {
        this.service = service;
        this.postService = postService;
    }

    @GetMapping("/writePre")
    public ModelAndView createPre(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("boardWrite");
        return modelAndView;
    }

    @PostMapping("/write")
    public ModelAndView create(@RequestParam("name") String name){
        ModelAndView modelAndView = new ModelAndView();
        Board board = new Board(name);
        this.service.createItem(board);
        modelAndView.setViewName("redirect:/board/list");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView viewAll(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("boardList", this.service.viewAll());
        modelAndView.setViewName("boardList");
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewOne(Model model, @PathVariable("id") int id){
        logger.info("id : " + id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("board", this.service.viewOne(id-1));
        modelAndView.addObject("postList", this.postService.viewAll());
        modelAndView.setViewName("postList");
        return modelAndView;
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") int id, Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("board", this.service.viewOne(id-1));
        modelAndView.setViewName("boardModify");
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") int id, @RequestParam("name") String name){
        ModelAndView modelAndView = new ModelAndView();
        Board board = new Board(name);
        this.service.modifyItem(id-1, board);
        modelAndView.setViewName("redirect:/board/list");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        this.service.deleteItem(id-1);
        modelAndView.setViewName("redirect:/board/list");
        return modelAndView;
    }
}
