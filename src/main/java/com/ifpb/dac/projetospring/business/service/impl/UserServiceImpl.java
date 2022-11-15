package com.ifpb.dac.projetospring.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.business.service.PasswordEncoderService;
import com.ifpb.dac.projetospring.business.service.SystemRoleService;
import com.ifpb.dac.projetospring.business.service.UserService;
import com.ifpb.dac.projetospring.model.entity.SystemRole;
import com.ifpb.dac.projetospring.model.entity.User;
import com.ifpb.dac.projetospring.model.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private PasswordEncoderService passwordEncoderService;

    private static final IllegalStateException NULL_ID_ILLEGAL_STATE_EXCEPTION = new IllegalStateException("ID cannot be null");

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            throw new IllegalStateException("User already exists");
        }

        passwordEncoderService.encryptPassword(user);

        List<SystemRole> roles = new ArrayList<>();
        roles.add(systemRoleService.findDefault());
        user.setRoles(roles);

        return repository.save(user);
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            throw NULL_ID_ILLEGAL_STATE_EXCEPTION;
        }

        passwordEncoderService.encryptPassword(user);

        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw NULL_ID_ILLEGAL_STATE_EXCEPTION;
        }

        User user = findById(id);

        if (user == null) {
            throw new IllegalStateException("Could not find a user with ID " + id);
        }

        repository.delete(user);

    }

    @Override
    public User findById(Long id) {
        if(id == null) {
			throw NULL_ID_ILLEGAL_STATE_EXCEPTION;
		}
		
		return repository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        if(email == null) {
			throw new IllegalStateException("Email cannot be null");
		}
		
		return repository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        if(username == null) {
			throw new IllegalStateException("Username cannot be null");
		}
		
		return repository.findByUsername(username).orElse(null);
    }

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> find(User filter) {
        Example<User> example = Example.of(
            filter, 
            ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING)
        );
		
		return repository.findAll(example);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find a user with username " + username);
        }

        return user;
    }

}
