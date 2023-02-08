package com.mycmsbackend.controller;

import com.mycmsbackend.exception.ResourceNotFoundException;
import com.mycmsbackend.domain.User;
import com.mycmsbackend.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
@Validated
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // find all users
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return usersService.getAllUsers();
    }

    // find one user
    @GetMapping("/users/{id}")
    public User getSingleUser(@PathVariable long id) {
        LOGGER.info(">>>>> Id to search is : " + id);
        return usersService
                .getSingleUser(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user with id : " + id));
    }
}
