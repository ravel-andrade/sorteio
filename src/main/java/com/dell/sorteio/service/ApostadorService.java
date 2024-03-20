package com.dell.sorteio.service;

import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.repository.ApostadorRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApostadorService {
    ApostadorRepository repository;
    public Apostador getPorNomeECPF(String nome, String cpf) {
        Apostador apostador = repository.getPorCPF(cpf);
        if(apostador == null || !apostador.getNome().equals(nome)){
            return null;
        }
        return apostador;
    }

    public Apostador cadastraApostador(Apostador apostadorEntrada) {
        repository.cadastraApostador(apostadorEntrada.getNome(), apostadorEntrada.getCpf());
        return repository.getPorCPF(apostadorEntrada.getCpf());
    }

    public List<Apostador> getApostadores(){
        return repository.getApostadores();
    }

    public void premiar(List<Apostador> vencedores) {
        vencedores.forEach(vencedor ->{
            repository.adicionaSaldo(vencedor.getSaldo()+10000.0, vencedor.getID());
        });
    }
}
