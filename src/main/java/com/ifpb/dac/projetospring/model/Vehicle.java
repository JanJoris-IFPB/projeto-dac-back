package com.ifpb.dac.projetospring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @Column(name = "plate", length = 7)
    private String plate;

    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String color;

    @Override
    public String toString() {
        return getMake() + " " + getModel() + "" +
                "(Placa='" + getPlate() + "'" +
                ", Cor='" + getColor() + "'" +
                ")";
    }

}
