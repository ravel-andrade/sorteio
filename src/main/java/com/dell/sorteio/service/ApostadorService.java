package com.dell.sorteio.service;

import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.repository.ApostadorRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ApostadorService {
    ApostadorRepository repository;
    public Apostador getPorNomeECPF(String nome, String cpf) {
        Apostador apostador = repository.getPorNomeECPF(nome, cpf);
        if(apostador==null){
            //exeption
        }
        return apostador;
    }
}
