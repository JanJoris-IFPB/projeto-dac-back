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
    public PersonDTO toDTO(Person person) {
        PersonDTO dto = new PersonDTO();

        dto.setCpf(person.getCpf());
        dto.setAge(person.getAge());
        dto.setName(person.getName());
        dto.setEmail(person.getEmail());
        dto.setPhone(person.getPhone());
        dto.setVehicleList(person.getVehicleList());

        return dto;
    }

    @Override
    public List<PersonDTO> toDTOList(Iterable<Person> personList) {
        List<PersonDTO> dtos = new ArrayList<>();

        for (Person person : personList)
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
