package com.ifpb.dac.projetospring.business.service;

import org.springframework.security.core.AuthenticationException;

import com.ifpb.dac.projetospring.model.entity.User;

public interface AuthenticationService {

	public String login(String username, String password) throws AuthenticationException;
	
	public User getLoggedUser();
	
}
