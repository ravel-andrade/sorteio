package com.dell.sorteio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table( name = "apostador" )
@EqualsAndHashCode
public class Apostador {
    @Id
    Integer ID;
    String nome;
    String cpf;
    Double saldo;

    public Apostador(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldo = 0.0;
    }
}
