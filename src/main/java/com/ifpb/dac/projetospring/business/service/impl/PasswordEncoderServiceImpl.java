package com.ifpb.dac.projetospring.business.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.business.service.PasswordEncoderService;
import com.ifpb.dac.projetospring.model.entity.User;

@Service
public class PasswordEncoderServiceImpl extends BCryptPasswordEncoder implements PasswordEncoderService {

    @Override
    public void encryptPassword(User user) {
        if(user.getPassword() != null) {
			user.setPassword(encode(user.getPassword()));
		}
    }
    
}
