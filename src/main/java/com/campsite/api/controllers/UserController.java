package com.campsite.api.controllers;

import com.campsite.api.client.User;
import com.campsite.api.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    final static Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser/create")
    public com.okta.sdk.resource.user.User registerUser(@RequestBody User user) {
        com.okta.sdk.resource.user.User result;
        try {
            return userService.registerUser(user);
        } catch (Exception ex) {
            LOGGER.error("Error while creating user: " + user.toString(), ex);
            throw ex;
        }
    }

    @GetMapping("/registerUser/{id}")
    public Object getUser(@PathVariable String id) {
        try {
            return userService.getUser(id);
        } catch (Exception ex) {
            LOGGER.error("Error getting user info with id: " + id, ex);
            throw ex;
        }
    }
}
