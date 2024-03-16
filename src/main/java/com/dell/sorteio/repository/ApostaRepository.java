package com.dell.sorteio.repository;

import com.dell.sorteio.model.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long> {
    @Query(value = "SELECT id FROM aposta", nativeQuery = true)
    List<Integer> buscaApostas();
}