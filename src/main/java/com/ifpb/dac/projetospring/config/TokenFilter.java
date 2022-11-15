package com.ifpb.dac.projetospring.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ifpb.dac.projetospring.business.service.TokenService;
import com.ifpb.dac.projetospring.business.service.UserService;
import com.ifpb.dac.projetospring.model.entity.User;

public class TokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserService userService;

    public TokenFilter(TokenService tokenService, UserService userService) {
        super();
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = tokenService.get(request);

        if (tokenService.isValid(token)) {
            authenticate(token);
        }

        filterChain.doFilter(request, response);

    }

    private void authenticate(String token) {
		User user = userService.findById(tokenService.getUserId(token));

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
