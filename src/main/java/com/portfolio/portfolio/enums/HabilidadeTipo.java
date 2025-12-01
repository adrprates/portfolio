package com.portfolio.portfolio.enums;

public enum HabilidadeTipo {
    INICIANTE("Iniciante"),
    INTERMEDIARIO("Intermediário"),
    AVANCADO("Avançado");

    private final String tipo;

    HabilidadeTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}