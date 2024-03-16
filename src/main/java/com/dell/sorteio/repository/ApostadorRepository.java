package com.dell.sorteio.repository;

import com.dell.sorteio.model.Apostador;

public interface ApostadorRepository {
    public Apostador getPorNomeECPF(String nome, String cpf);
}
