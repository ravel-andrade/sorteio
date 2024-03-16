package com.dell.sorteio.utils;

import com.dell.sorteio.dto.ApostaDTO;
import com.dell.sorteio.model.Apostador;

public class ToModel {

    public static Apostador getApostadorFromApostaDTO(ApostaDTO apostaDTO) {
        if(apostaDTO.getNomeApostador() == null || apostaDTO.getCpfApostador() == null){
            //exception
        }
        return new Apostador(apostaDTO.getNomeApostador(), apostaDTO.getCpfApostador());
    }
}
