package mission3.mission3.service;

import mission3.mission3.dao.UserDao;
import mission3.mission3.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(@Autowired UserDao userDao){
        this.userDao = userDao;
    }

    public void createUser(UserDto userDto){
        this.userDao.createUser(userDto);
    }

    public UserDto readUser(int id){
        return this.userDao.readUser(id);
    }
}
