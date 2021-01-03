package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

// USER ���� SQL ó��
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmailAndPw(String email, String pw);
	Optional<User> findByEmail(String email);

}
