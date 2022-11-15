package com.ifpb.dac.projetospring.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.dac.projetospring.business.service.DTOService;
import com.ifpb.dac.projetospring.business.service.UserService;
import com.ifpb.dac.projetospring.model.entity.User;
import com.ifpb.dac.projetospring.presentation.dto.UserDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DTOService<User, UserDTO> userDTOService;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {
        User entity = userDTOService.toModel(dto);
        entity = userService.save(entity);

        dto = userDTOService.toDTO(entity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @RequestBody UserDTO dto) {
        dto.setId(id);

        User entity = userDTOService.toModel(dto);
        entity = userService.update(entity);

        dto = userDTOService.toDTO(entity);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> find(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "id", required = false) Long id) {

        User filter = new User();
        filter.setId(id);
        filter.setName(name);
        filter.setEmail(email);

        List<User> entities = userService.find(filter);
        List<UserDTO> dtos = userDTOService.toDTOList(entities);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(Exception e) {
        return e.getMessage();
    }

}
