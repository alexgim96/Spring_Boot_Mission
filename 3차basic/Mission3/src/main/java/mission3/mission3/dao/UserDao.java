package mission3.mission3.dao;

import mission3.mission3.dto.UserDto;
import mission3.mission3.entity.UserEntity;
import mission3.mission3.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private final UserRepository userRepository;

    public UserDao(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setAge(userDto.getAge());
        this.userRepository.save(userEntity);
    }

    public UserDto readUser(int id){
        Optional<UserEntity> userEntity = this.userRepository.findById((long) id);
        if(userEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new UserDto(
                Math.toIntExact(userEntity.get().getId()),
                userEntity.get().getName(),
                userEntity.get().getAge(),
                userEntity.get().getPostEntityList()
        );
    }

}
