package com.testTema.domain.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BARALHOS")
public class Baralhos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_baralho")
    private Long idBaralho;

    @NotNull
    @Column(unique = true, name = "nom_baralho")
    private String nomBaralho;

}