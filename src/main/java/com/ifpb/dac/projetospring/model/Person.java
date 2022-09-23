package com.ifpb.dac.projetospring.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "people")
public class Person {

    @Id @Column(name = "CPF", length = 14)
    private String cpf;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true, length = 14)
    private String phone;

    @Override
    public String toString() {
        return getName() + 
            " (" +
            "CPF='" + getCpf() + "'" +
            ", Nome='" + getName() + "'" +
            ", Idade='" + getAge() + "'" +
            ", Email='" + getEmail() + "'" +
            ", Telefone='" + getPhone() + "'" +
            ")";
    }
   

}
