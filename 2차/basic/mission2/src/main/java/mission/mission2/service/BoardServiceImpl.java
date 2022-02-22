package mission.mission2.service;

import mission.mission2.entity.Board;
import mission.mission2.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class BoardServiceImpl implements Service<Board>{

    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
    private final Repository<Board> repository;

    public BoardServiceImpl(@Autowired Repository<Board> repository) {
        this.repository = repository;
    }

    @Override
    public void createItem(Board item) {
        this.repository.save(item);
    }

    @Override
    public Board viewOne(int id) {
        return this.repository.readOne(id);
    }

    @Override
    public List<Board> viewAll() {
        return this.repository.readAll();
    }

    @Override
    public void modifyItem(int id, Board item) {
        this.repository.update(id, item);
    }

    @Override
    public void deleteItem(int id) {
        this.repository.delete(id);
    }
}
