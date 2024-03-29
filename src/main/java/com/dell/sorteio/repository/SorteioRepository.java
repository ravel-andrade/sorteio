package com.dell.sorteio.repository;

import com.dell.sorteio.BO.ApostaBO;
import com.dell.sorteio.model.Sorteio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Long> {

    @Query(value = "SELECT * FROM sorteio where aberto = true", nativeQuery = true)
    public Sorteio getSorteioAberto();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO sorteio(nome, aberto, aberto_aposta) values(:nome, true, true)", nativeQuery = true)
    public void abreSorteio(@Param("nome") String nome);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sorteio set aberto= false, aberto_aposta = false where aberto = true", nativeQuery = true)
    public void fechaSorteio();

    @Modifying
    @Transactional
    @Query(value = "UPDATE sorteio set aberto_aposta= false where aberto_aposta = true", nativeQuery = true)
    public void fechaAposta();

    @Query(value = "SELECT * FROM sorteio where aberto = true and aberto_aposta = true", nativeQuery = true)
    public Sorteio getSorteioApostasAberto();
}
