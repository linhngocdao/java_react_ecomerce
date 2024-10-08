package com.tutorial.api.mvc.api.controllers;

import com.tutorial.api.mvc.api.dto.UserRequest;
import com.tutorial.api.mvc.api.dto.UserUpdateRequest;
import com.tutorial.api.mvc.api.models.ResponseObject;
import com.tutorial.api.mvc.api.models.User;
import com.tutorial.api.mvc.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    ResponseEntity<ResponseObject> createUser(@RequestBody UserRequest request) {
        try {
            User createdUser = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject("Success", "User created successfully", createdUser)
            );
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Username already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponseObject("Error", "Username already exists", null)
                );
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Error", "User creation failed", null)
            );
        }
    }

    @GetMapping
    ResponseEntity<ResponseObject> getAllUser() {
        try {
            List<User> users = userService.getAllUser();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Users fetched successfully", users)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("Error", "Failed to fetch users", null)
            );
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getUserById(@PathVariable String id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "User fetched successfully", user)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Error", "User not found", null)
            );
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        try {
            User updatedUser = userService.updateUser(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "User updated successfully", updatedUser)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Error", "User not found", null)
            );
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "User deleted successfully", null)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Error", "User not found", null)
            );
        }
    }
}
