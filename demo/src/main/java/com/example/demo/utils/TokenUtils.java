package com.example.demo.utils;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.example.demo.app.user.domain.User;
import com.example.demo.enums.role.UserRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class TokenUtils {
	
	private static final String secretKey = "JwtSecretKeyExample";
	
	public static String generateJwtToken(User user) {
		JwtBuilder builder = Jwts.builder()
				.setSubject(user.getEmail())
				.setHeader(createHeader())
				.setClaims(createClaims(user))
				.setExpiration(createExpireDateForOneYear())
				.signWith(SignatureAlgorithm.HS256, createSigningKey());
		
		return builder.compact();
	}
	
	public static boolean isValidToken(String token) {
		try {
			Claims claims = getClaimsFormToken(token);
			System.out.println("expireTime :" + claims.getExpiration());
			System.out.println("email :" + claims.get("email"));
			System.out.println("role :" + claims.get("role"));
			return true;
		}catch (ExpiredJwtException exception) {
			System.out.println("Token Expired");
			return false;
		}catch (JwtException exception) {
			System.out.println("Token Tampered");
			return false;
		}catch (NullPointerException exception) {
			System.out.println("Token is null");
			return false;
		}
	}
	
	public static String getTokenFromHeader(String header) {
		return header.split(" ")[1];
	}
	
	private static Date createExpireDateForOneYear() {
        // 토큰 만료시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

	
	private static Map<String, Object> createHeader(){
		Map<String, Object> header = new HashMap<>();
		
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		header.put("regDate", System.currentTimeMillis());
		
		return header;
	}
	
	private static Map<String, Object> createClaims(User user){
		//공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("email", claims);
		claims.put("role", claims);
		
		return claims;
	}
	
	private static Key createSigningKey() {
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
		return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
	}
	
	private static Claims getClaimsFormToken(String token) {
		return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
				.parseClaimsJws(token).getBody();
	}
	
	private static String getUserEmailFromToken(String token) {
		Claims claims = getClaimsFormToken(token);
		return (String)claims.get("email");
	}
	
	private static UserRole getRoleFromToken(String token) {
		Claims claims = getClaimsFormToken(token);
		return (UserRole)claims.get("role");
	}

}
