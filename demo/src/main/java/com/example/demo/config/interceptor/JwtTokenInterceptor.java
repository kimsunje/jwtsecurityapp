package com.example.demo.config.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.utils.TokenUtils;
import com.exmaple.demo.constants.AuthConstants;

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
