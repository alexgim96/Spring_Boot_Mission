package mission.mission2.service;

import mission.mission2.entity.Post;
import mission.mission2.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class PostServiceImpl implements Service<Post>{

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private final Repository<Post> postRepository;

    public PostServiceImpl(@Autowired Repository<Post> postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createItem(Post item) {
        this.postRepository.save(item);
    }

    @Override
    public Post viewOne(int id) {
        return this.postRepository.readOne(id);
    }

    @Override
    public List<Post> viewAll() {
        return this.postRepository.readAll();
    }

    @Override
    public void modifyItem(int id, Post item) {
        this.postRepository.update(id, item);
    }

    @Override
    public void deleteItem(int id) {
        this.postRepository.delete(id);
    }
}
