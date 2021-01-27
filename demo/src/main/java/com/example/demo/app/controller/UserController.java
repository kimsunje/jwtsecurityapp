package com.example.demo.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.app.domain.User;
import com.example.demo.app.service.UserService;
import com.example.demo.enums.role.UserRole;
import com.example.demo.utils.TokenUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
@Log4j2
public class UserController {
	
	private final BCryptPasswordEncoder passwordEncoder;
	private final UserService userService;
	
	@PostMapping(value = "signUp")
	public ResponseEntity signUp(@RequestBody User user) {
		
		user.setRole(UserRole.ROLE_USER);
		user.setPw(passwordEncoder.encode(user.getPw()));
		return userService.findByEmail(user.getEmail()).isPresent()
				? ResponseEntity.badRequest().build()
				: ResponseEntity.ok(TokenUtils.generateJwtToken(userService.signUp(user)));
	}
	
	@GetMapping(value = "/findAll")
	public ResponseEntity findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	
	
	

}
