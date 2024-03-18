package com.dell.sorteio.service;

import com.dell.sorteio.model.Aposta;
import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.model.Sorteio;
import com.dell.sorteio.repository.ApostaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApostaService {
    ApostaRepository apostaRepository;
    SorteioService sorteioService;
    ApostadorService apostadorService;

    public List<Aposta> getApostasSorteioAberto() {
        if(!sorteioService.existeSorteioAberto()){
            throw new RuntimeException("Não existe sorteio aberto a apostas");
        }
        Sorteio sorteioAberto = sorteioService.getSorteioAberto();
        return apostaRepository.buscaApostasPorSorteioId(sorteioAberto.getID());
    }

    public List<Aposta> getApostas() {
        return apostaRepository.buscaApostas();
    }
    public void apostar(List<Integer> numeros, Boolean surpresinha, Apostador apostadorEntrada) {
        Apostador apostador= apostadorService.getPorNomeECPF(apostadorEntrada.getNome(), apostadorEntrada.getCpf());
        if(apostador == null){
            apostador = apostadorService.cadastraApostador(apostadorEntrada);
        }
        if(!sorteioService.existeSorteioAbertoApostas()){
            throw new RuntimeException("Não existe sorteio aberto a apostas");
        }
        if(numerosInvalidos(numeros)){
            throw new RuntimeException("Numeros invalidos");
        }
        if(surpresinha){
          numeros = sorteioService.geraNumeros();
        }
        Sorteio sorteioAberto = sorteioService.getSorteioAberto();
        apostaRepository.criarAposta(apostador.getID(),sorteioAberto.getID(),listToString(numeros));
    }

    public void fechaAposta(){
        sorteioService.fechaAposta();
    }

    private boolean numerosInvalidos(List<Integer> entrada) {
        Set<Integer> numeros = new HashSet<>(entrada);
        if (numeros.size() != 5 || entrada.size() != 5) {
            return true;
        }
        return false;
    }



    private String listToString(List<Integer> numeros){
        return numeros.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}
