package mission.mission2.repository;

import mission.mission2.entity.Board;
import mission.mission2.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class PostMemoryRepository implements Repository<Post>{

    private static final Logger logger = LoggerFactory.getLogger(PostMemoryRepository.class);
    private final List<Post> postList;

    public PostMemoryRepository() {
        this.postList = new ArrayList<>();
    }

    @Override
    public void save(Post item) {
        this.postList.add(item);
    }

    @Override
    public List<Post> readAll() {
        return this.postList;
    }

    @Override
    public Post readOne(int id) {
        return this.postList.get(id);
    }

    @Override
    public void update(int id, Post item) {
        Post target = this.postList.get(id);
        if(item.getTitle() != null){
            target.setTitle(item.getTitle());
        }
        if(item.getContent() != null){
            target.setContent(item.getContent());
        }
        this.postList.set(id, target);
    }

    @Override
    public void delete(int id) {
        this.postList.remove(id);
    }
}
