package com.campsite.api.controllers;

import com.campsite.api.client.User;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.UserBuilder;
import com.okta.sdk.resource.user.UserList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    final static Logger LOGGER = Logger.getLogger(UserController.class);

    @Autowired
    private Client client;

    @PostMapping("/registerUser/create")
    public com.okta.sdk.resource.user.User registerUser(@RequestBody User user) {
        com.okta.sdk.resource.user.User result;
        try {
            result = UserBuilder.instance()
                    .setEmail(user.getEmail())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setPassword(user.getPassword().toCharArray())
                    .buildAndCreate(client);
            return result;
        } catch (Exception ex) {
            LOGGER.error("Error while creating user: " + user.toString(), ex);
        }
        return null;
    }

    @GetMapping("/registerUser/{id}")
    public Object getUser(@PathVariable String id) {
        com.okta.sdk.resource.user.User result = null;
        try {
            UserList users = client.listUsers();
            for (com.okta.sdk.resource.user.User user : users) {
                if(user.getId().equals(id)) {
                    result = user;
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error getting user info with id: " + id, ex);
        }
        return result;
    }
}
