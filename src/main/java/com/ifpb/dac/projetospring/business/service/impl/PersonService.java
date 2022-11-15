package com.ifpb.dac.projetospring.business.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.business.service.EntityService;
import com.ifpb.dac.projetospring.model.entity.Person;
import com.ifpb.dac.projetospring.model.repository.PersonRepository;

@Service
public class PersonService implements EntityService<Person, String> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(String cpf) {
        return personRepository.findById(cpf);
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

}
