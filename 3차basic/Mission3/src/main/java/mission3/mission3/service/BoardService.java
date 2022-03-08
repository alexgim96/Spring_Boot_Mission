package mission3.mission3.service;

import mission3.mission3.dao.BoardDao;
import mission3.mission3.dto.BoardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
    private final BoardDao boardDao;

    public BoardService(@Autowired BoardDao boardDao){
        this.boardDao = boardDao;
    }

    public void createBoard(BoardDto boardDto){
        this.boardDao.createBoard(boardDto);
    }

    public BoardDto readBoard(int id){
        return this.boardDao.readBoard(id);
    }

    public List<BoardDto> readAllBoard(){
        return this.boardDao.readAllBoard();
    }

    public void updateBoard(int id, BoardDto boardDto){
        this.boardDao.updateBoard(id, boardDto);
    }

    public void deleteBoard(int id){
        this.boardDao.deleteBoard(id);
    }
}
