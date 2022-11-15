package com.ifpb.dac.projetospring.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifpb.dac.projetospring.model.entity.SystemRole;

@Repository
public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {

    Optional<SystemRole> findByName(String name);
    
}
