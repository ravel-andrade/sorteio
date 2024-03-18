package com.dell.sorteio.service;

import com.dell.sorteio.BO.ApostaBO;
import com.dell.sorteio.model.Aposta;
import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.model.Sorteio;
import com.dell.sorteio.repository.ApostaRepository;
import com.dell.sorteio.repository.SorteioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class SorteioService {
    SorteioRepository repository;
    ApostaRepository apostaRepository;
    public void abreSorteio(String nome){
        Sorteio sorteio = repository.getSorteioAberto();

        if(sorteio != null){
            throw new RuntimeException("Já existe sorteio aberto");
        }

        repository.abreSorteio(nome);
    }

    public void fechaSorteio(){
        repository.fechaSorteio();
    }

    public void fechaAposta(){
        repository.fechaAposta();
    }

    public boolean existeSorteioAbertoApostas() {
        if(repository.getSorteioApostasAberto() ==null){
            return false;
        }
        return true;
    }

    public Sorteio getSorteioAberto(){
        return repository.getSorteioAberto();
    }

    public List<Apostador> sortear() {
        Sorteio sorteio = repository.getSorteioAberto();
        if(repository.getSorteioAberto() ==null){
            throw new RuntimeException("Não existe aposta aberta");
        }
        List<Aposta> apostasBanco = apostaRepository.buscaApostasPorSorteioId(sorteio.getID());
        List<ApostaBO> apostas = ApostaBO.convertFromEntity(apostasBanco);
        for(int i = 0; i<50; i++){
            List<Integer> valoresSorteados = geraNumerosFake();
            List<Apostador> vencedores = buscaVencedores(valoresSorteados, apostas);
            if(!vencedores.isEmpty()){
                return vencedores;
            }
            }

        return Collections.emptyList();
    }

    private List<Integer> geraNumerosFake() {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        return a;
    }

    private List<Apostador> buscaVencedores(List<Integer> valoresSorteados, List<ApostaBO> apostas) {
        List<ApostaBO> apostasVencedoras = apostas.stream()
                .filter(aposta -> aposta.getNumeros().containsAll(valoresSorteados)).toList();
        List<Apostador> vencedores = new ArrayList<>();

        apostasVencedoras.forEach(aposta -> vencedores.add(aposta.getApostador()));
        return vencedores;
    }

    public List<Integer> geraNumeros() {
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
