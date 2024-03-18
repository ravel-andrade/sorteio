package com.dell.sorteio.dto;

import com.dell.sorteio.model.Apostador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {
    List<Apostador> Vencedores;
    List<String> numerosSorteados;
    Map<Integer, Integer> frequenciaAposta;
    int numApostas;
    int numRodadas;

}
