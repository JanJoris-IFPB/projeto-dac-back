package com.ifpb.dac.projetospring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifpb.dac.projetospring.model.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    public boolean existsByEmail(String email);
    public boolean existsByPhone(String phone);
    
}
