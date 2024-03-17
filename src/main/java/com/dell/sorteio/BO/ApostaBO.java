package com.dell.sorteio.BO;

import com.dell.sorteio.model.Aposta;
import com.dell.sorteio.model.Apostador;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApostaBO {
    Apostador apostador;
    List<Integer> numeros;


    public ApostaBO(Apostador apostador, String numeros) {
        this.apostador = apostador;
        Arrays.stream(numeros.split(",")).forEach( numero ->{
            this.numeros.add(Integer.parseInt(numero));
        });
    }

    public static List<ApostaBO> convertFromEntity(List<Aposta> apostasBanco) {
        List<ApostaBO> apostas = new ArrayList<>();
        apostasBanco.forEach(apostaBanco -> {
            apostas.add(new ApostaBO(apostaBanco.getApostador(), apostaBanco.getNumeros()));
        });
        return apostas;
    }
}
