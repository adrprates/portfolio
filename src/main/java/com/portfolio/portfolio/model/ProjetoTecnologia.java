package com.portfolio.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "projeto_tecnologias")
public class ProjetoTecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tecnologia_id")
    private Tecnologia tecnologia;
}