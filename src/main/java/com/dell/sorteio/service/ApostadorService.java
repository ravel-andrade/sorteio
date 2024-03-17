package com.dell.sorteio.service;

import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.repository.ApostadorRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApostadorService {
    ApostadorRepository repository;
    public Apostador getPorNomeECPF(String nome, String cpf) {
        Apostador apostador = repository.getPorCPF(cpf);
        if(!apostador.getNome().equals(nome)){
            return null;
        }
        return apostador;
    }

    public Apostador cadastraApostador(Apostador apostadorEntrada) {
        return repository.save(apostadorEntrada);
    }
}
