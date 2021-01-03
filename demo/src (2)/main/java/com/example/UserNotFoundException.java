package com.example;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(String email) {
		super(email + "NotFoundException");
	}

}
