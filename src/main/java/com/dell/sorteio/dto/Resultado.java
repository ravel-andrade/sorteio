package com.dell.sorteio.dto;

import com.dell.sorteio.model.Apostador;

import java.util.List;
import java.util.Map;

public class Resultado {
    List<ApostadorDTO> Vencedores;
    String numerosVencedores;
    Map<Integer, Integer> frequenciaAposta;
    int numApostas;

}
