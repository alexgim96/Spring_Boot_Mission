package mission.mission2.repository;

import mission.mission2.entity.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class BoardMemoryRepositoryImpl implements Repository<Board>{

    private static final Logger logger = LoggerFactory.getLogger(BoardMemoryRepositoryImpl.class);
    private final List<Board> boardList;

    public BoardMemoryRepositoryImpl() {
        this.boardList = new ArrayList<>();
    }

    @Override
    public void save(Board board) {
        this.boardList.add(board);
    }

    @Override
    public List<Board> readAll() {
        return this.boardList;
    }

    @Override
    public Board readOne(int id) {
        return this.boardList.get(id);
    }

    @Override
    public void update(int id, Board board) {
        Board target = this.boardList.get(id);
        if(board.getName() != null){
            target.setName(board.getName());
        }
        this.boardList.set(id, target);
    }

    @Override
    public void delete(int id) {
        this.boardList.remove(id);
    }
}
