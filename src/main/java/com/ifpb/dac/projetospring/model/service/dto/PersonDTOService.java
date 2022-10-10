package com.ifpb.dac.projetospring.model.service.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.model.dto.PersonDTO;
import com.ifpb.dac.projetospring.model.entity.Person;

@Service
public class PersonDTOService {

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

    public List<PersonDTO> toDTOList(Iterable<Person> personList) {
        List<PersonDTO> dtos = new ArrayList<>();

        for (Person person : personList)
            dtos.add(toDTO(person));

        return dtos;
    }

    public Person toPerson(PersonDTO personDTO) {
        Person person = new Person();

        person.setCpf(personDTO.getCpf());
        person.setAge(personDTO.getAge());
        person.setName(personDTO.getName());
        person.setEmail(personDTO.getEmail());
        person.setPhone(personDTO.getPhone());
        person.setVehicleList(personDTO.getVehicleList());

        return person;
    }
    
}
