package com.portfolio.portfolio.model;

import com.portfolio.portfolio.enums.HabilidadeTipo;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "habilidades")
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "habilidade_tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private HabilidadeTipo habilidadeTipo;
}