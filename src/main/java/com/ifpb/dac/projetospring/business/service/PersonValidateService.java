package com.ifpb.dac.projetospring.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.model.repository.PersonRepository;

@Service
public class PersonValidateService {

    @Autowired
    private PersonRepository personRepository;

    public void validateCpf(String cpf) throws Exception {
        if (personRepository.existsById(cpf))
            throw new Exception("Pessoa com mesmo CPF já cadastrada.");
    }

    public void validateAge(int age) throws Exception {
        if (age < 18)
            throw new Exception("Usuário menor de idade.");
    }

    public void validateEmail(String email) throws Exception {
        if (personRepository.existsByEmail(email))
            throw new Exception("Pessoa com mesmo email já cadastrada.");
    }

    public void validatePhone(String phone) throws Exception {
        if (personRepository.existsByPhone(phone))
            throw new Exception("Pessoa com mesmo telefone já cadastrada.");
    }
    
}
