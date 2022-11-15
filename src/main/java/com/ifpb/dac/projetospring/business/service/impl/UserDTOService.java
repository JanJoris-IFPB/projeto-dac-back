package com.ifpb.dac.projetospring.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.business.service.DTOService;
import com.ifpb.dac.projetospring.model.entity.SystemRole;
import com.ifpb.dac.projetospring.model.entity.User;
import com.ifpb.dac.projetospring.presentation.dto.UserDTO;

@Service
public class UserDTOService implements DTOService<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        List<String> roles = new ArrayList<>();
        if (user.getRoles() != null) {
            for (SystemRole role : user.getRoles()) {
                roles.add(role.getName());
            }
        }
        dto.setRoles(roles);

        return dto;
    }

    @Override
    public List<UserDTO> toDTOList(Iterable<User> userList) {
        List<UserDTO> dtos = new ArrayList<>();

        for (User user : userList) {
            dtos.add(toDTO(user));
        }

        return dtos;
    }

    @Override
    public User toModel(UserDTO dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }

}
