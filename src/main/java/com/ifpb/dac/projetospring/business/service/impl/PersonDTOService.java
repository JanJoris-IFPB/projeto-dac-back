package com.ifpb.dac.projetospring.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.business.service.DTOService;
import com.ifpb.dac.projetospring.model.entity.Person;
import com.ifpb.dac.projetospring.presentation.dto.PersonDTO;

@Service
public class PersonDTOService implements DTOService<Person, PersonDTO> {

    @Override
    public PersonDTO toDTO(Person model) {
        PersonDTO dto = new PersonDTO();

        dto.setCpf(model.getCpf());
        dto.setAge(model.getAge());
        dto.setName(model.getName());
        dto.setEmail(model.getEmail());
        dto.setPhone(model.getPhone());
        dto.setVehicleList(model.getVehicleList());

        return dto;
    }

    @Override
    public List<PersonDTO> toDTOList(Iterable<Person> modelList) {
        List<PersonDTO> dtos = new ArrayList<>();

        for (Person person : modelList)
            dtos.add(toDTO(person));

        return dtos;
    }

    @Override
    public Person toModel(PersonDTO dto) {
        Person person = new Person();

        person.setCpf(dto.getCpf());
        person.setAge(dto.getAge());
        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPhone(dto.getPhone());
        person.setVehicleList(dto.getVehicleList());

        return person;
    }
    
}
