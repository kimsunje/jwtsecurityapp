package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.domain.User;

public interface UserService {

    User signUp(User user);

    Optional<User> findByEmail(String email);

    List<User> findAll();

}	