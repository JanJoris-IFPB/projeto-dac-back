package com.ifpb.dac.projetospring.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.model.entity.Person;
import com.ifpb.dac.projetospring.model.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> findById(String cpf) {
        return personRepository.findById(cpf);
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public long count() {
        return personRepository.count();
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
