package com.ifpb.dac.projetospring.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.ifpb.dac.projetospring.business.service.AuthenticationService;
import com.ifpb.dac.projetospring.business.service.DTOService;
import com.ifpb.dac.projetospring.business.service.TokenService;
import com.ifpb.dac.projetospring.business.service.UserService;
import com.ifpb.dac.projetospring.model.entity.User;
import com.ifpb.dac.projetospring.presentation.dto.LoginDTO;
import com.ifpb.dac.projetospring.presentation.dto.TokenDTO;
import com.ifpb.dac.projetospring.presentation.dto.UserDTO;

@RestController
@RequestMapping("/api/auth")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private DTOService<User, UserDTO> userDTOService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        String token = authenticationService.login(loginDTO.getUsername(), loginDTO.getPassword());

        User user = userService.findByUsername(loginDTO.getUsername());
        UserDTO userDTO = userDTOService.toDTO(user);

        TokenDTO tokenDTO = new TokenDTO(token, userDTO);

        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }

    @PostMapping("/isTokenValid")
    public ResponseEntity<Boolean> isTokenValid(@RequestBody TokenDTO dto) {
        return new ResponseEntity<>(tokenService.isValid(dto.getToken()), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(Exception e) {
        return e.getMessage();
    }

}
