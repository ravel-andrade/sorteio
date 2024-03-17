package com.dell.sorteio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table( name = "Aposta" )
@EqualsAndHashCode
public class Aposta {
    @Id
    Integer ID;
    String valoresApostados;
    @ManyToOne
    Apostador apostador;

    public Aposta(List<Integer> valoresApostados, Apostador apostador){
        this.valoresApostados = valoresApostados.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        this.apostador = apostador;
    }
}
