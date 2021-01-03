package com.example.demo.config.security;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.app.user.domain.MyUserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Resource(name="userDetailsService")
	private UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
		//AuthenticationFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
		String userEmail = token.getName();
		String userPw = (String)token.getCredentials();
		//UserDetailsService를 통해 DB에서 아이디로 사용자 조회
		MyUserDetails userDetails = (MyUserDetails)userDetailsService.loadUserByUsername(userEmail);
      //MyUserDetails userDetails = (MyUserDetails)userDetailsService.loadUserByUserName(userEmail);
		if(!passwordEncoder.matches(userPw, userDetails.getPassword())) {
			throw new BadCredentialsException(userDetails.getUsername() + "Invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, userPw, userDetails.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
