package com.ifpb.dac.projetospring.model.entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "people")
public class Person {

    @Id
    @Column(name = "CPF", length = 14)
    private String cpf;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true, length = 14)
    private String phone;

    @OneToMany(targetEntity = Vehicle.class, fetch = FetchType.EAGER)
    private List<Vehicle> vehicleList = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(
                getName() +
                        " (" +
                        "CPF='" + getCpf() + "'" +
                        ", Nome='" + getName() + "'" +
                        ", Idade='" + getAge() + "'" +
                        ", Email='" + getEmail() + "'" +
                        ", Telefone='" + getPhone() + "'" +
                        ")\n");

        sb.append("=".repeat(20));

        sb.append("\nVeículos:\n");
        for (Vehicle vehicle : vehicleList) {
            sb.append(vehicle + "\n");
        }

        return sb.toString();
    }

}
