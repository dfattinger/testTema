package com.testTema.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BARALHOS_CARTAS")
public class BaralhosCartas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_baralho_carta")
    private Long idBaralhoCarta;

    @ManyToOne
    @JoinColumn(name = "id_baralho")
    public Baralhos baralhos;

    @ManyToOne
    @JoinColumn(name = "id_carta")
    public Cartas cartas;

}