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
@Table( name = "aposta" )
@EqualsAndHashCode
public class Aposta {
    @Id
    Integer ID;
    String numeros;
    @ManyToOne
    @JoinColumn(name = "sorteio_id")
    Sorteio sorteio;
    @ManyToOne
    @JoinColumn(name = "apostador_id")
    Apostador apostador;

    public Aposta(List<Integer> valoresApostados, Apostador apostador){
        this.numeros = valoresApostados.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        this.apostador = apostador;
    }
}
