package mission3.mission3.dao;

import mission3.mission3.dto.BoardDto;
import mission3.mission3.dto.PostDto;
import mission3.mission3.entity.BoardEntity;
import mission3.mission3.repository.BoardRepository;
import org.apache.catalina.LifecycleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardDao {
    private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);
    private final BoardRepository boardRepository;

    public BoardDao(@Autowired BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public void createBoard(BoardDto boardDto){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(boardDto.getName());
        this.boardRepository.save(boardEntity);
    }

    public BoardDto readBoard(int id){
        Optional<BoardEntity> boardEntity = this.boardRepository.findById((long) id);
        if(boardEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        BoardDto boardDto = new BoardDto(
                Math.toIntExact(boardEntity.get().getId()),
                boardEntity.get().getName(),
                boardEntity.get().getPostEntityList()
        );
        return boardDto;
    }

    public List<BoardDto> readAllBoard(){
        Iterator<BoardEntity> iterator = this.boardRepository.findAll().iterator();
        List<BoardDto> list = new ArrayList<>();
        while(iterator.hasNext()){
            BoardEntity boardEntity = iterator.next();
            BoardDto boardDto = new BoardDto(
                    Math.toIntExact(boardEntity.getId()),
                    boardEntity.getName(),
                    boardEntity.getPostEntityList()
            );
            list.add(boardDto);
        }
        return list;
    }

    public void updateBoard(int id, BoardDto boardDto){
        Optional<BoardEntity> targetEntity = this.boardRepository.findById((long) id);
        if(targetEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        BoardEntity boardEntity = targetEntity.get();
        boardEntity.setName(boardDto.getName() == null ? boardEntity.getName() : boardDto.getName());
        this.boardRepository.save(boardEntity);
    }

    public void deleteBoard(int id){
        Optional<BoardEntity> boardEntity = this.boardRepository.findById((long) id);
        if(boardEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.boardRepository.delete(boardEntity.get());
    }
}
