package com.dell.sorteio.service;

import com.dell.sorteio.BO.ApostaBO;
import com.dell.sorteio.dto.ApostadorDTO;
import com.dell.sorteio.dto.Resultado;
import com.dell.sorteio.model.Aposta;
import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.model.Sorteio;
import com.dell.sorteio.repository.ApostaRepository;
import com.dell.sorteio.repository.SorteioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class SorteioService {
    SorteioRepository repository;
    ApostaRepository apostaRepository;
    ApostadorService apostadorService;
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

    public boolean existeSorteioAberto() {
        if(repository.getSorteioAberto() ==null){
            return false;
        }
        return true;
    }

    public Sorteio getSorteioAberto(){
        return repository.getSorteioAberto();
    }

    public Resultado sortear() {
        Sorteio sorteio = repository.getSorteioAberto();
        if(repository.getSorteioAberto() ==null){
            throw new RuntimeException("Não existe aposta aberta");
        }
        List<Aposta> apostasBanco = apostaRepository.buscaApostasPorSorteioId(sorteio.getID());
        List<ApostaBO> apostas = ApostaBO.convertFromEntity(apostasBanco);

        Resultado resultado = new Resultado();
        List<String> todosValoresSorteados = new ArrayList<>();
        resultado.setNumApostas(apostas.size());
        resultado.setFrequenciaAposta(geraFrequencia(apostas));
        for(int i = 0; i<25; i++){
            List<Integer> valoresSorteados = geraNumerosFake();
            todosValoresSorteados.add(valoresSorteados.toString());
            List<Apostador> vencedores = buscaVencedores(valoresSorteados, apostas);
            resultado.setNumRodadas(i+1);
            if(!vencedores.isEmpty()){
                apostadorService.premiar(vencedores);
                resultado.setVencedores(vencedores);
                resultado.setNumerosSorteados(todosValoresSorteados);
                return resultado;
            }
        }
        resultado.setNumerosSorteados(todosValoresSorteados);
        return resultado;
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

    private Map<Integer, Integer> geraFrequencia(List<ApostaBO> apostas) {
        Map<Integer, Integer> frequencia = new HashMap<>();
        apostas.forEach(aposta -> {
            aposta.getNumeros().forEach(numero -> {
                if(!frequencia.containsKey(numero)){
                    frequencia.put(numero, 1);
                }
                else{
                    frequencia.put(numero, frequencia.get(numero)+1);
                }
            });

        });
        return frequencia;
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
