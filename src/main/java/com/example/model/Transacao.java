package com.example.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Transacao {

    java.util.Date utilDate = new java.util.Date();
    
    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private String tipo;
    private double valor;
    private Date data;
    private String cpf;

    // Construtor vazio
    public Transacao() {
    }

    // Construtor com argumentos
    public Transacao(String tipo, double valor, Date data, String cpf) {

        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.cpf = cpf;
    }

    // Métodos Getter e Setter
    
    public void setCpfUsuario(String cpfUsuario) {
        this.cpf = cpfUsuario;
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

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public String getCPF() {
        return cpf;
    }
    public String setCpf(String cpf) {
        return cpf;
    }

    public void setData(LocalDateTime localDateTime) {
    }
}
