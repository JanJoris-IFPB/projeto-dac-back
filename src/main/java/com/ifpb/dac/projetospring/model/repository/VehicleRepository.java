package com.ifpb.dac.projetospring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifpb.dac.projetospring.model.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    Iterable<Vehicle> findAllByMake(String make);
    Iterable<Vehicle> findAllByModel(String model);
    Iterable<Vehicle> findAllByColor(String color);
    
}
