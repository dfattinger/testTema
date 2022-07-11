package com.testTema.domain.repository;

import com.testTema.domain.entity.Baralhos;

import com.testTema.domain.entity.BaralhosCartas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BaralhosCartasRepository extends JpaRepository<BaralhosCartas, Long> {
    List<BaralhosCartas> findByBaralhosIdBaralho(Long idBaralho);

    List<BaralhosCartas> findByBaralhosIdBaralhoAndCartasIdCarta(Long idBaralho, Long idCarta);

    List<BaralhosCartas> findByBaralhosNomBaralhoContainsIgnoreCase(String nomBaralho);

    List<BaralhosCartas> findByCartasClassesNomClasseContainsIgnoreCase(String nomClasse);

    List<BaralhosCartas> findByCartasClassesIdClasseInAndBaralhosIdBaralho(List<Long> listIdClasse, Long idBaralho);

    @Transactional
    @Modifying
    @Query(value = " delete from BaralhosCartas b where b.baralhos.idBaralho = :idBaralho")
    void deleteByBaralhosIdBaralho(Long idBaralho);
}