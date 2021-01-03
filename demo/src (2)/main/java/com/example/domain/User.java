package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.example.enums.role.UserRole;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "USER")
@Getter
public class User extends Common implements Serializable{
	
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
	@Setter
	@Column(nullable = false)
	private String pw;
	
	@Setter
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	
	
}
