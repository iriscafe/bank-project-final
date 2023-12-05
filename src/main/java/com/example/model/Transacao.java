package com.example.model;

import java.sql.Date;

public class Transacao {

    private String cpfUsuario;
    private String tipo;
    private double valor;

    // Construtor vazio
    public Transacao() {
    }

    // Construtor com argumentos
    public Transacao(String cpfUsuario, String tipo, double valor) {
        this.cpfUsuario = cpfUsuario;
        this.tipo = tipo;
        this.valor = valor;
    }

    // Métodos Getter e Setter
    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(Date date) {
    }
}
