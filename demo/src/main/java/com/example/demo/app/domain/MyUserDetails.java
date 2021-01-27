package com.example.demo.app.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;


@AllArgsConstructor
@Getter
public class MyUserDetails implements UserDetails{
	
	@Delegate
	private User user;
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return user.getPw();
	}
	
	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return user.getIsEnable();
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return user.getIsEnable();
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return user.getIsEnable();
	}

	@Override
	public boolean isEnabled() {
		return user.getIsEnable();
	}
}
