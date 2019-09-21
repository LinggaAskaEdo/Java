package org.example.springboot.mongodb.controller;

import org.example.springboot.mongodb.model.User;
import org.example.springboot.mongodb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class UserController
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    private static final String USER_NOT_FOUND = "User not found.";

    @Autowired
    public UserController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        logger.info("Getting all users.");

        return userRepository.findAll();
    }

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable String userId)
    {
        logger.info("Getting user with ID: {}.", userId);

        return userRepository.findOne(userId);
    }

    @PostMapping(value = "/create")
    public User addNewUsers(@RequestBody User user)
    {
        logger.info("Saving user.");

        return userRepository.save(user);
    }

    @GetMapping(value = "/settings/{userId}")
    public Object getAllUserSettings(@PathVariable String userId)
    {
        User user = userRepository.findOne(userId);

        if (null != user)
        {
            return user.getUserSettings();
        }
        else
        {
            return USER_NOT_FOUND;
        }
    }

    @GetMapping(value = "/settings/{userId}/{key}")
    public String getUserSetting(@PathVariable String userId, @PathVariable String key)
    {
        User user = userRepository.findOne(userId);

        if (null != user)
        {
            return user.getUserSettings().get(key);
        }
        else
        {
            return USER_NOT_FOUND;
        }
    }

    @GetMapping(value = "/settings/{userId}/{key}/{value}")
    public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value)
    {
        User user = userRepository.findOne(userId);

        if (null != user)
        {
            user.getUserSettings().put(key, value);
            userRepository.save(user);

            return "Key added";
        }
        else
        {
            return USER_NOT_FOUND;
        }
    }
}