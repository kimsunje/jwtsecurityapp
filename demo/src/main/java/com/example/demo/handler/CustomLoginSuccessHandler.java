package com.example.demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.example.demo.app.user.domain.MyUserDetails;
import com.example.demo.app.user.domain.User;
import com.example.demo.utils.TokenUtils;
import com.exmaple.demo.constants.AuthConstants;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		User user = ((MyUserDetails)authentication.getPrincipal()).getUser();
		String token = TokenUtils.generateJwtToken(user);
		response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + token);
	}

}
