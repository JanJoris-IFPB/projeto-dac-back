package com.ifpb.dac.projetospring.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ifpb.dac.projetospring.model.Person;
import com.ifpb.dac.projetospring.service.PersonService;
import com.ifpb.dac.projetospring.service.validation.PersonValidateService;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonValidateService personValidateService;

    public Person savePerson(String cpf, String name, int age, String email, String phone) throws Exception {
        Person person = personService.findById(cpf).orElse(new Person());

        personValidateService.validateCpf(cpf);
        personValidateService.validateAge(age);
        personValidateService.validateEmail(email);
        personValidateService.validatePhone(phone);

        person.setCpf(cpf);
        person.setName(name);
        person.setAge(age);
        person.setEmail(email);
        person.setPhone(phone);

        return personService.save(person);
    }

    public Person getPerson(String cpf) throws NoSuchElementException {
        return personService.findById(cpf).orElseThrow();
    }

    public Iterable<Person> getAll() throws Exception {
        if (personService.count() == 0)
            throw new Exception("Não há pessoas cadastradas!");

        return personService.findAll();
    }

    public void delete(String cpf) throws NoSuchElementException {
        personService.delete(getPerson(cpf));
    }

}
