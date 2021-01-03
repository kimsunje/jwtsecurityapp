package com.example.security.config.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.security.constants.AuthConstants;
import com.example.token.TokenUtils;

public class JwtTokenInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		String header = request.getHeader(AuthConstants.AUTH_HEADER);
		
		if(header != null) {
			String token = TokenUtils.getTokenFromHeader(header);
			if(TokenUtils.isValidToken(token)) {
				return true;
			}
		}
		response.sendRedirect("/error/unauthorized");
		return false;
	}

}
