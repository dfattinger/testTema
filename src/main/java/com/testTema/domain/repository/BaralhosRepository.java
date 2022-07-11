package com.testTema.domain.repository;

import com.testTema.domain.entity.Baralhos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaralhosRepository extends JpaRepository<Baralhos, Long> {
    List<Baralhos> findByNomBaralhoContainsIgnoreCase(String nomBaralho);
}