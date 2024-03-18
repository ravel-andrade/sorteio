package com.dell.sorteio.repository;

import com.dell.sorteio.model.Apostador;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Long> {
    @Query(value = "Select * from apostador a where cpf=:cpf",nativeQuery = true)
    public Apostador getPorCPF(@Param("cpf") String cpf);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO apostador(nome, cpf) values(:nome, :cpf)", nativeQuery = true)
    public void cadastraApostador(@Param("nome") String nome, @Param("cpf") String cpf);

    @Modifying
    @Transactional
    @Query(value = "UPDATE apostador set saldo = :saldo where ID = :id", nativeQuery = true)
    public void adicionaSaldo(@Param("saldo") Double saldo, @Param("id") int id);
}
