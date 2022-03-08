package mission3.mission3.service;

import mission3.mission3.dao.PostDao;
import mission3.mission3.dto.PostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
    private final PostDao postDao;

    public PostService(@Autowired PostDao postDao){
        this.postDao = postDao;
    }

    public void createPost(int boardId, PostDto postDto){
        this.postDao.createPost(boardId, postDto);
    }

    public PostDto readPost(int boardId, int id){
        return this.postDao.readPost(boardId ,id);
    }

    public List<PostDto> readAllPost(int boardId){
        return this.postDao.readAllPost(boardId);
    }

    public void updatePost(int id, PostDto postDto){
        this.postDao.updatePost(id, postDto);
    }

    public void deletePost(int id){
        this.postDao.deletePost(id);
    }
}
