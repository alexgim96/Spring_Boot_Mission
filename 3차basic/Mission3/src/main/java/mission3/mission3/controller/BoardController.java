package mission3.mission3.controller;


import mission3.mission3.dto.BoardDto;
import mission3.mission3.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private final BoardService boardService;

    public BoardController(@Autowired BoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createBoard(@RequestBody BoardDto boardDto){
        this.boardService.createBoard(boardDto);
    }

    @GetMapping("/{id}")
    public BoardDto readBoard(@PathVariable("id") int id){
        return this.boardService.readBoard(id);
    }

    @GetMapping()
    public List<BoardDto> readAllBoard(){
        return this.boardService.readAllBoard();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateBoard(@PathVariable("id") int id, @RequestBody BoardDto boardDto){
        this.boardService.updateBoard(id, boardDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBoard(@PathVariable("id") int id){
        this.boardService.deleteBoard(id);
    }

}
