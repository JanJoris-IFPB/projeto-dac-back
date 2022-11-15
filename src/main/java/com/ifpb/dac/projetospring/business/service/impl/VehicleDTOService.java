package com.ifpb.dac.projetospring.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ifpb.dac.projetospring.business.service.DTOService;
import com.ifpb.dac.projetospring.model.entity.Vehicle;
import com.ifpb.dac.projetospring.presentation.dto.VehicleDTO;

@Service
public class VehicleDTOService implements DTOService<Vehicle, VehicleDTO> {

    @Override
    public VehicleDTO toDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();

        vehicleDTO.setPlate(vehicle.getPlate());
        vehicleDTO.setMake(vehicle.getMake());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setColor(vehicle.getColor());

        return vehicleDTO;
    }

    @Override
    public List<VehicleDTO> toDTOList(Iterable<Vehicle> vehicleList) {
        List<VehicleDTO> dtos = new ArrayList<>();

        for (Vehicle vehicle : vehicleList)
            dtos.add(toDTO(vehicle));

        return dtos;
    }

    @Override
    public Vehicle toModel(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();

        vehicle.setPlate(dto.getPlate());
        vehicle.setMake(dto.getMake());
        vehicle.setModel(dto.getModel());
        vehicle.setColor(dto.getColor());

        return vehicle;
    }

}
