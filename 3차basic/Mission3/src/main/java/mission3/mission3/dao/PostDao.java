package mission3.mission3.dao;

import mission3.mission3.dto.PostDto;
import mission3.mission3.entity.BoardEntity;
import mission3.mission3.entity.PostEntity;
import mission3.mission3.repository.BoardRepository;
import mission3.mission3.repository.PostRepository;
import mission3.mission3.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Repository
public class PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public PostDao(@Autowired PostRepository postRepository, @Autowired BoardRepository boardRepository, @Autowired UserRepository userRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public void createPost(int boardId, PostDto postDto){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContent(postDto.getContent());
        postEntity.setWriter(this.userRepository.findById((long)postDto.getWriter()).get());
        postEntity.setBoardEntity(boardRepository.findById((long)boardId).get());
        this.postRepository.save(postEntity);
    }

    // return을 PostDto로 한 이유 : readPost의 출력값은 service 클래스와의 상호작용이기 때문
    public PostDto readPost(int boardId, int id){
        Optional<PostEntity> postEntity = this.postRepository.findById(Long.valueOf(id));
        if(postEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Optional<BoardEntity> board = boardRepository.findById((long) boardId);
        List<PostEntity> list = board.get().getPostEntityList();
        Iterator<PostEntity> iterator = list.iterator();
        while(iterator.hasNext()){
            PostEntity targetEntity = iterator.next();
            if(Objects.equals(targetEntity.getId(), Long.valueOf(id))){
                return new PostDto(
                        Math.toIntExact(targetEntity.getId()),
                        targetEntity.getTitle(),
                        targetEntity.getContent(),
                        Math.toIntExact(targetEntity.getWriter().getId()),
                        Math.toIntExact(targetEntity.getBoardEntity().getId())
                );
            }
        }
        return new PostDto();
    }

    public List<PostDto> readAllPost(int boardId){
        Iterator<PostEntity> iterator = this.postRepository.findAll().iterator();
        List<PostDto> list = new ArrayList<>();
        while(iterator.hasNext()){
            PostEntity postEntity = iterator.next();
            if(Objects.equals(postEntity.getBoardEntity().getId(), Long.valueOf(boardId))){
                PostDto postDto = new PostDto(
                        Math.toIntExact(postEntity.getId()),
                        postEntity.getTitle(),
                        postEntity.getContent(),
                        Math.toIntExact(postEntity.getWriter().getId()),
                        Math.toIntExact(postEntity.getBoardEntity().getId())
                );
                list.add(postDto);
            }
        }
        return list;
    }

    public void updatePost(int id, PostDto postDto){
        Optional<PostEntity> targetEntity = this.postRepository.findById((long) id);
        if(targetEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        PostEntity postEntity = targetEntity.get();
        postEntity.setTitle(postDto.getTitle() == null ? postEntity.getTitle() : postDto.getTitle());
        postEntity.setContent(postDto.getContent() == null ? postEntity.getContent() : postDto.getContent());
        this.postRepository.save(postEntity);
    }

    public void deletePost(int id){
        Optional<PostEntity> targetEntity = this.postRepository.findById((long) id);
        if(targetEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        this.postRepository.delete(targetEntity.get());
    }
}
