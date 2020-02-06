package com.campsite.api.controllers;

import com.campsite.api.pojos.User;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.UserBuilder;
import com.okta.sdk.resource.user.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private Client client;

    @PostMapping("/registerUser/create")
    public String registerUser(@RequestBody User user) {
        com.okta.sdk.resource.user.User result;
        try {
            result = UserBuilder.instance()
                    .setEmail(user.getEmail())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setPassword(user.getPassword().toCharArray())
                    .buildAndCreate(client);
            System.out.println(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "hello world";
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
            ex.printStackTrace();
        }
        return result;
    }
}
