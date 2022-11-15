package com.ifpb.dac.projetospring.business.service;

import javax.servlet.http.HttpServletRequest;

import com.ifpb.dac.projetospring.model.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface TokenService {

	String generate(User user);
	
	Claims getClaims(String token) throws ExpiredJwtException;
	
	boolean isValid(String token);
	
	String getUsername(String token);
	
	Long getUserId(String token);
	
	String get(HttpServletRequest request);
	
}
