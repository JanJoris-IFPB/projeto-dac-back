package com.ifpb.dac.projetospring.business.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.business.service.EntityService;
import com.ifpb.dac.projetospring.model.entity.Vehicle;
import com.ifpb.dac.projetospring.model.repository.VehicleRepository;

@Service
public class VehicleService implements EntityService<Vehicle, String> {
    
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> findById(String plate) {
        return vehicleRepository.findById(plate);
    }

    @Override
    public Iterable<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public long count() {
        return vehicleRepository.count();
    }

    @Override
    public void delete(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }

}
