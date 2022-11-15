package com.ifpb.dac.projetospring.presentation.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.dac.projetospring.business.service.DTOService;
import com.ifpb.dac.projetospring.business.service.EntityService;
import com.ifpb.dac.projetospring.model.entity.Vehicle;
import com.ifpb.dac.projetospring.presentation.dto.VehicleDTO;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private EntityService<Vehicle, String> vehicleService;
    @Autowired
    private DTOService<Vehicle, VehicleDTO> vehicleDTOService;

    @PostMapping()
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleService.findById(
                vehicleDTO.getPlate()).orElse(new Vehicle());

        vehicle.setPlate(vehicleDTO.getPlate());
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setColor(vehicleDTO.getColor());

        return new ResponseEntity<>(
                vehicleDTOService.toDTO(vehicleService.save(vehicle)),
                HttpStatus.CREATED);
    }

    @GetMapping("/find/plate={plate}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable("plate") String plate) throws NoSuchElementException {
        return new ResponseEntity<>(
                vehicleDTOService.toDTO(vehicleService.findById(plate).orElseThrow()),
                HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<VehicleDTO>> getAll() throws Exception {
        if (vehicleService.count() == 0)
            throw new Exception("Não há veículos cadastrados!");

        return new ResponseEntity<>(
                vehicleDTOService.toDTOList(vehicleService.findAll()),
                HttpStatus.FOUND);
    }

    @PutMapping("/update/plate={plate}")
    public ResponseEntity<VehicleDTO> update(@PathVariable String plate, @RequestBody VehicleDTO vehicleDTO)
            throws NoSuchElementException {
        Vehicle vehicle = vehicleService.findById(plate).orElseThrow();

        if (vehicleDTO.getMake() != null)
            vehicle.setMake(vehicleDTO.getMake());

        if (vehicleDTO.getModel() != null)
            vehicle.setModel(vehicleDTO.getModel());

        if (vehicleDTO.getColor() != null)
            vehicle.setColor(vehicleDTO.getColor());

        return new ResponseEntity<>(
                vehicleDTOService.toDTO(vehicleService.save(vehicle)),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/plate={plate}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable("plate") String plate) throws NoSuchElementException {
        vehicleService.delete(vehicleService.findById(plate).orElseThrow());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody String handleNoSuchElement() {
        return "Veículo(s) não encontrado(s)";
    }

}