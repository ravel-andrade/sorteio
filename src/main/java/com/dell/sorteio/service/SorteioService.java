package com.dell.sorteio.service;

import com.dell.sorteio.model.Sorteio;
import com.dell.sorteio.repository.SorteioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SorteioService {
    SorteioRepository repository;
    public void abreSorteio(String nome){
        Sorteio sorteio = repository.getSorteioAberto();

        if(sorteio != null){
            //exception
        }

        repository.abreSorteio(nome);
    }

    public void fechaSorteio(){
        repository.fechaSorteio();
    }

    public boolean existeSorteioAberto() {
        if(repository.getSorteioAberto() ==null){
            return false;
        }
        return true;
    }
}
