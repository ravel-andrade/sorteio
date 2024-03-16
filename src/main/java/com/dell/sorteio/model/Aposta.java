package com.dell.sorteio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    List<Integer> valoresApostados;
    @ManyToOne
    Apostador apostador;

    public Aposta(List<Integer> valoresApostados, Apostador apostador){
        this.valoresApostados = valoresApostados;
        this.apostador = apostador;
    }
}
