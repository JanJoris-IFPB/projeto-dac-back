package com.ifpb.dac.projetospring.presentation.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private List<String> roles;
    
}
