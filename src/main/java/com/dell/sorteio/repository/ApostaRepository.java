package com.dell.sorteio.repository;

import com.dell.sorteio.model.Aposta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long> {

    @Modifying
    @Transactional
    @Query(value = "Insert into aposta(apostador_id, sorteio_id, numeros) values(:apostadorId, :sorteioId, :numeros)", nativeQuery = true)
    void criarAposta(@Param("apostadorId") Integer apostadorId,
                              @Param("sorteioId") Integer sorteioId,
                              @Param("numeros") String numeros);

    @Query(value = "select * from aposta where sorteio_id = :sorteioId", nativeQuery = true)
    List<Aposta> buscaApostasPorSorteioId(@Param("sorteioId") Integer sorteioId);

    @Query(value = "select * from aposta", nativeQuery = true)
    List<Aposta> buscaApostas();
}