package com.app.security;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {
	@Value("${JWT_SECRET_KEY}")
	private String jwtSecretKey;
	@Value("${JWT_EXP_TIMEOUT}")
	private String jwtExpTimeout;
	
	
	public String generateJwtToken(Authentication authentication) {
		CustomUserDetails userPrincipal = (CustomUserDetails)authentication.getPrincipal();
		try {
			String jwtToken = Jwts.builder()
					.setSubject(userPrincipal.getUsername())
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + Integer.parseInt(jwtExpTimeout)))
					.signWith(SignatureAlgorithm.HS512, jwtSecretKey)
					.compact();
			return jwtToken;
		}catch(Throwable ex) {
			ex.printStackTrace();
		}
		return "";
	}
	
	public boolean validateJwtToken(String jwtToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken);
			return true;
		}catch(Exception ex) {
			// Log Error on console
		}
		return false;
	}
	
	public String getUserNameFromJwtToken(String jwtToken) {
		var username = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken).getBody().getSubject();
		return username;
	}

}