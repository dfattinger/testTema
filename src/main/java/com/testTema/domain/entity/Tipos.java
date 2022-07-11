package com.testTema.domain.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TIPOS")
public class Tipos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long idTipo;

    @NotNull
    @Column(unique = true, name = "nom_tipo")
    private String nomTipo;
}
