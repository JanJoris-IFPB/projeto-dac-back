package com.ifpb.dac.projetospring.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ifpb.dac.projetospring.model.Vehicle;
import com.ifpb.dac.projetospring.service.VehicleService;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    public Vehicle saveVehicle(String plate, String make, String model, String color) {
        Vehicle vehicle = vehicleService.findById(plate).orElse(new Vehicle());

        vehicle.setPlate(plate);
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setColor(color);

        return vehicleService.save(vehicle);
    }

    public Vehicle getVehicle(String plate) throws NoSuchElementException {
        return vehicleService.findById(plate).orElseThrow();
    }

    public Iterable<Vehicle> getAll() throws Exception {
        if (vehicleService.count() == 0)
            throw new Exception("Não há veículos cadastrados!");

        return vehicleService.findAll();
    }

    public void delete(String plate) throws NoSuchElementException {
        vehicleService.delete(getVehicle(plate));
    }
    
}
