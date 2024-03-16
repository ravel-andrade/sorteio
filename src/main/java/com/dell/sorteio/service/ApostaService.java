package com.dell.sorteio.service;

import com.dell.sorteio.model.Aposta;
import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.repository.ApostaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ApostaService {
    ApostaRepository apostaRepository;
    SorteioService sorteioService;
    ApostadorService apostadorService;


    public void apostar(List<Integer> numeros, Boolean surpresinha, Apostador apostadorEntrada) {
        Apostador apostador= apostadorService.getPorNomeECPF(apostadorEntrada.getNome(), apostadorEntrada.getCpf());
        if(!sorteioService.existeSorteioAberto()){
            //exception
        }
        if(numerosInvalidos(numeros)){
            System.out.println("numerosInvalido");
        }
        if(surpresinha){
          numeros = geraNumeros();
        }
        apostaRepository.save(new Aposta(numeros, apostador));
    }

    private boolean numerosInvalidos(List<Integer> entrada) {
        Set<Integer> numeros = new HashSet<>(entrada);
        if (numeros.size() != 5 || entrada.size() != 5) {
            return true;
        }
        return false;
    }

    private List<Integer> geraNumeros() {
        Random random = new Random();
        List<Integer> numeros =  new ArrayList<>();
        for(int i=0; i<5; i++){
            int valor = random.nextInt(50)+1;
            if(numeros.contains(valor)){
                i--;
            }else{
                numeros.add(valor);
            }
        }
        return numeros;
    }
}
