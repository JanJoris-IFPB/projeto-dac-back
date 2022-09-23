package com.ifpb.dac.projetospring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ifpb.dac.projetospring.model.Vehicle;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, String> {

    Iterable<Vehicle> findAllByMake(String make);
    Iterable<Vehicle> findAllByModel(String model);
    Iterable<Vehicle> findAllByColor(String color);
    
}
