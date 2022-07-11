package com.testTema.domain.repository;

import com.testTema.domain.entity.Cartas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartasRepository extends JpaRepository<Cartas, Long> {
    List<Cartas> findByNomCartaContainsIgnoreCase(String nomCarta);

    List<Cartas> findByClassesNomClasseContainsIgnoreCase(String nomClasse);

    List<Cartas> findByTiposNomTipoContainsIgnoreCase(String nomTipo);
}