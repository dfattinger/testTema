package com.testTema.domain.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CARTAS")
public class Cartas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carta")
    private Long idCarta;

    @NotNull
    @Column(unique = true, name = "nom_carta")
    private String nomCarta;

    @NotNull
    @Column(unique = true, name = "dsc_carta")
    private String dscCarta;

    @NotNull
    @Column(name = "val_ataque")
    private Long valAtaque;

    @NotNull
    @Column(name = "val_defesa")
    private Long valDefesa;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipos tipos;

    @ManyToOne
    @JoinColumn(name = "id_classe")
    private Classes classes;

}