package com.dell.sorteio.repository;

import com.dell.sorteio.model.Apostador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostadorRepository extends JpaRepository<Apostador, Long> {
    @Query(value = "Select * from Apostador a where cpf=:cpf",nativeQuery = true)
    public Apostador getPorCPF(@Param("cpf") String cpf);
}
