package com.tutorial.api.mvc.api.services;

import com.tutorial.api.mvc.api.dto.UserRequest;
import com.tutorial.api.mvc.api.dto.UserUpdateRequest;
import com.tutorial.api.mvc.api.models.User;
import com.tutorial.api.mvc.api.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepositories userRepositories;

    public User createUser(UserRequest request) {
        User user = new User();
        if(userRepositories.existsAllByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        user.setUsername(request.getUsername());
        user.setFullName(request.getFullName());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setStatus(request.isStatus());

        return userRepositories.save(user);
    }

    public List<User> getAllUser() {
        return userRepositories.findAll();
    }

    public User getUserById(String id) {
        return userRepositories.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String id, UserUpdateRequest request) {
        User user = getUserById(id);
        user.setFullName(request.getFullName());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setStatus(request.isStatus());
        return userRepositories.save(user);
    }

    public void deleteUser(String id) {
        User user = getUserById(id);
        userRepositories.delete(user);
    }

}
