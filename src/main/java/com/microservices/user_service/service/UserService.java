package com.microservices.user_service.service;

import com.microservices.user_service.model.User;
import com.microservices.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }
}
