package com.ifpb.dac.projetospring.business.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ifpb.dac.projetospring.model.entity.User;

public interface PasswordEncoderService extends PasswordEncoder {

    void encryptPassword(User user);

}
