package com.example.nossoappe.model;

public class Gasto {

    private long id;
    private String nome;
    private double valor;
    private boolean pago;
    private long idMorador;

    public void setIdMorador(long idMorador) {
        this.idMorador = idMorador;
    }
    public long getIdMorador() {
        return idMorador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
    public void setPago(boolean pago) {
        this.pago = pago;
    }
    public boolean isPago() {
        return pago;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId(){
        return id;
    }


}
