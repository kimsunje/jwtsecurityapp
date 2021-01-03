package com.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.example.domain.MyUserDetails;
import com.example.domain.User;
import com.example.security.constants.AuthConstants;
import com.example.token.TokenUtils;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		User user = ((MyUserDetails)authentication.getPrincipal()).getUser();
		String token = TokenUtils.generateJwtToken(user);
		response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + token);
	}

}
