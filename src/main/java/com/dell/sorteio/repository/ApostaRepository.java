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
    @Query(value = "SELECT id FROM aposta", nativeQuery = true)
    List<Integer> buscaApostas();

    @Modifying
    @Transactional
    @Query(value = "Insert into Aposta(apostadorId, numeros) values(:apostadorId, :numeros)", nativeQuery = true)
    void criarAposta(@Param("apostadorId") Integer apostadorId,
                              @Param("numeros") String numeros);
}