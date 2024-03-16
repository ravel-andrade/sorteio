package com.dell.sorteio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class ApostaDTO {
    List<Integer> numeros;
    Boolean surpresinha;
    String nomeApostador;
    String cpfApostador;

}
