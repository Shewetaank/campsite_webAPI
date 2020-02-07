package com.campsite.api.service;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;
import com.okta.sdk.resource.user.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private Client client;

    public User registerUser(com.campsite.api.client.User user) {
        return UserBuilder.instance()
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPassword(user.getPassword().toCharArray())
                .buildAndCreate(client);
    }

    public User getUser(String id) {
        UserList users = client.listUsers();
        for (com.okta.sdk.resource.user.User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
