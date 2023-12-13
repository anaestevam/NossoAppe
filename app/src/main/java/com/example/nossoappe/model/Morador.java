package com.example.nossoappe.model;

public class Morador {

    private String nome;
    private long id;
    private double porcentagem;

    public void setNome(String nome) { this.nome= nome; }

    public void setId(long id) { this.id= id; }

    public void setPorcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public double getPorcentagem(){ return porcentagem; }
}

