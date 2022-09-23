package com.ifpb.dac.projetospring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ifpb.dac.projetospring.model.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {

    public boolean existsByEmail(String email);
    public boolean existsByPhone(String phone);
    
}
