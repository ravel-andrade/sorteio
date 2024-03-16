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
@Table( name = "Sorteio" )
@EqualsAndHashCode
public class Sorteio {
    @Id
    Integer ID;
    String nome;
    Boolean aberto = true;
}
