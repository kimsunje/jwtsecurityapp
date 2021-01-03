package com.example.demo.app.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.app.user.domain.User;
import com.example.demo.app.user.repository.UserRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {

    @NonNull
    private UserRepository userRepository;

    @Override
    public User signUp(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
