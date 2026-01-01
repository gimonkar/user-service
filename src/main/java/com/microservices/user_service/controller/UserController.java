package com.microservices.user_service.controller;

import com.microservices.user_service.model.User;
import com.microservices.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("add_user")
    public ResponseEntity<String> create(@RequestBody User user) {
        service.createUser(user);
        return ResponseEntity.ok("User created");
    }

    @GetMapping("get_user")
    public List<User> getAll() {
        return service.getUsers();
    }
}

