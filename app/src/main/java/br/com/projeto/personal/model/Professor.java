package br.com.projeto.personal.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Professor {

    private String nome;
    private String experiencia;
    private List<String> habilidades = new ArrayList<>();
    private int reputação;

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

    public List<String> getHabilidades() {
        return habilidades;
    }

    public int getReputação() {
        return reputação;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nome;
    }
}
