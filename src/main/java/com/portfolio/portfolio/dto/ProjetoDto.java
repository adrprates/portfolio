package com.portfolio.portfolio.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjetoDto {
    private Integer id;
    private String titulo;
    private String descricao;
    private String link;
    private List<Integer> tecnologiaIds;
}
