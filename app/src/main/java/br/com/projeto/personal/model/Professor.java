package br.com.projeto.personal.model;

import androidx.annotation.NonNull;

public class Professor {

    private String nome;
    private String experiencia;

    public Professor(String nome, String experiencia) {
        this.nome = nome;
        this.experiencia = experiencia;
    }

    public String getNome() {
        return nome;
    }

    public String getExperiencia() {
        return experiencia;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nome;
    }
}
