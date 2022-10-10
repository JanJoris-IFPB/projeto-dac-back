package com.ifpb.dac.projetospring.model.service.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.model.dto.VehicleDTO;
import com.ifpb.dac.projetospring.model.entity.Vehicle;

@Service
public class VehicleDTOService {

    public VehicleDTO toDto(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();

        vehicleDTO.setPlate(vehicle.getPlate());
        vehicleDTO.setMake(vehicle.getMake());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setColor(vehicle.getColor());

        return vehicleDTO;
    }

    public List<VehicleDTO> toDtoList(Iterable<Vehicle> vehicleList) {
        List<VehicleDTO> dtos = new ArrayList<>();

        for (Vehicle vehicle : vehicleList)
            dtos.add(toDto(vehicle));

        return dtos;
    }

    public Vehicle toVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();

        vehicle.setPlate(vehicleDTO.getPlate());
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setColor(vehicleDTO.getColor());

        return vehicle;
    }

}
