package com.dell.sorteio.dto;

import com.dell.sorteio.model.Aposta;
import com.dell.sorteio.model.Apostador;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ApostadorDTO {
    String nome;

    public static List<ApostadorDTO> fromModel(List<Apostador> vencedores) {
        List<ApostadorDTO> apostadores = new ArrayList<>();
        vencedores.forEach(vencedor -> {
            apostadores.add(new ApostadorDTO(vencedor.getNome()));
        });
        return apostadores;
    }
}
