package com.example.demo.app.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.app.domain.User;

public interface UserService {

    User signUp(User user);

    Optional<User> findByEmail(String email);

    List<User> findAll();

}	