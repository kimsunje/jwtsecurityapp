package com.example.demo.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.app.domain.User;

// USER 관련 SQL 처리
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmailAndPw(String email, String pw);
	Optional<User> findByEmail(String email);

}
