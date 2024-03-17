package com.dell.sorteio.repository;

import com.dell.sorteio.model.Sorteio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Long> {

    @Query(value = "SELECT * FROM Sorteio where aberto = true", nativeQuery = true)
    public Sorteio getSorteioAberto();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Sorteio(nome, aberto) values(':nome', true)", nativeQuery = true)
    public void abreSorteio(@Param("nome") String nome);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Sorteio set aberto= false where aberto = true", nativeQuery = true)
    public void fechaSorteio();
}
