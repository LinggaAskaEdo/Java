package springboot.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springboot.jdbc.service.UserService;

@RestController
public class UserController
{
    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping(value = "/v1/user/{user-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable("user-id") String userId)
    {
        return userService.getUser(userId);
    }

    @GetMapping(value = "/v1/user-liveness/{user-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserLiveness(@PathVariable("user-id") String userId)
    {
        return userService.getUserLiveness(userId);
    }
}