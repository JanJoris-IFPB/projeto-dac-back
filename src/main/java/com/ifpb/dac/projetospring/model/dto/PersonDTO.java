package com.ifpb.dac.projetospring.model.dto;

import java.util.List;

import com.ifpb.dac.projetospring.model.entity.Vehicle;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PersonDTO {

    private String cpf;
    private String name;
    private int age;
    private String email;
    private String phone;
    private List<Vehicle> vehicleList = new ArrayList<>();

}
