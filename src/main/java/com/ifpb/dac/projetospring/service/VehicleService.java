package com.ifpb.dac.projetospring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.model.Vehicle;
import com.ifpb.dac.projetospring.repository.VehicleRepository;

@Service
public class VehicleService {
    
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findById(String plate) {
        return vehicleRepository.findById(plate);
    }

    public Iterable<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public long count() {
        return vehicleRepository.count();
    }

    public void delete(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }

}
