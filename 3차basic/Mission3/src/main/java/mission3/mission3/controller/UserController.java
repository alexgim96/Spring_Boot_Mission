package mission3.mission3.controller;

import mission3.mission3.dto.UserDto;
import mission3.mission3.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto){
        this.userService.createUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto readUser(@PathVariable("id") int id){
        return this.userService.readUser(id);
    }
}
