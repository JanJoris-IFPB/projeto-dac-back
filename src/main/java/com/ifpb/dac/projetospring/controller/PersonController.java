package com.ifpb.dac.projetospring.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.dac.projetospring.model.dto.PersonDTO;
import com.ifpb.dac.projetospring.model.entity.Person;
import com.ifpb.dac.projetospring.model.entity.Vehicle;
import com.ifpb.dac.projetospring.model.service.PersonService;
import com.ifpb.dac.projetospring.model.service.VehicleService;
import com.ifpb.dac.projetospring.model.service.dto.PersonDTOService;
import com.ifpb.dac.projetospring.model.service.validation.PersonValidateService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonDTOService personDtoService;
    @Autowired
    private PersonValidateService personValidateService;
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO personDTO) throws Exception {
        personValidateService.validateCpf(personDTO.getCpf());
        personValidateService.validateAge(personDTO.getAge());
        personValidateService.validateEmail(personDTO.getEmail());
        personValidateService.validatePhone(personDTO.getPhone());

        Person person = personService.findById(
                personDTO.getCpf()).orElse(new Person());

        person.setCpf(personDTO.getCpf());
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());
        person.setEmail(personDTO.getEmail());
        person.setPhone(personDTO.getPhone());
        person.setVehicleList(new ArrayList<>());

        return new ResponseEntity<>(
                personDtoService.toDTO(personService.save(person)),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PersonDTO> getPerson(@RequestParam String cpf) throws NoSuchElementException {
        return new ResponseEntity<>(
                personDtoService.toDTO(personService.findById(cpf).orElseThrow()),
                HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<PersonDTO>> getAll() throws Exception {
        if (personService.count() == 0)
            throw new Exception("Não há pessoas cadastradas!");

        return new ResponseEntity<>(
                personDtoService.toDTOList(personService.findAll()),
                HttpStatus.FOUND);
    }

    @PutMapping("/update/cpf={cpf}")
    public ResponseEntity<PersonDTO> update(@PathVariable("cpf") String cpf, @RequestBody PersonDTO personDTO)
            throws NoSuchElementException {
        Person person = personService.findById(cpf).orElseThrow();

        if (personDTO.getName() != null)
            person.setName(personDTO.getName());

        if (personDTO.getAge() >= 18)
            person.setAge(personDTO.getAge());

        if (personDTO.getEmail() != null)
            person.setEmail(personDTO.getEmail());

        if (personDTO.getPhone() != null)
            person.setPhone(personDTO.getPhone());

        return new ResponseEntity<>(
                personDtoService.toDTO(personService.save(person)),
                HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String cpf) throws NoSuchElementException {
        personService.delete(personService.findById(cpf).orElseThrow());
    }

    @PutMapping("/associateVehicle")
    public ResponseEntity<PersonDTO> associateVehicle(@RequestParam String cpf,
            @RequestParam String plate) throws NoSuchElementException {
        Person person = personService.findById(cpf).orElseThrow();
        Vehicle vehicle = vehicleService.findById(plate).orElseThrow();

        person.getVehicleList().add(vehicle);

        return new ResponseEntity<>(
                personDtoService.toDTO(personService.save(person)),
                HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody String handleNoSuchElement() {
        return "Pessoa(s) não encontrada(s)";
    }

}
