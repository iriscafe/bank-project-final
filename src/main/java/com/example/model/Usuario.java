package com.example.model;

import javax.servlet.http.HttpSession;

public class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String telefone;

    private static final String USUARIO_ID_SESSION_KEY = "usuarioId";
    private static final String USUARIO_CPF_SESSION_KEY = "usuarioCpf";

    public static void setIdUsuario(HttpSession session, int usuarioId) {
        session.setAttribute(USUARIO_ID_SESSION_KEY, usuarioId);
    }

    public static int getIdUsuario(HttpSession session) {
        Object usuarioIdObj = session.getAttribute(USUARIO_ID_SESSION_KEY);
        return usuarioIdObj != null ? (int) usuarioIdObj : 0; // 0 ou outro valor padrão, se não estiver definido
    }

    public static void setCPFUsuario(HttpSession session, String usuarioCpf) {
        session.setAttribute(USUARIO_CPF_SESSION_KEY, usuarioCpf);
    }

    public static String getCPFUsuario(HttpSession session) {
        Object usuarioCpfObj = session.getAttribute(USUARIO_CPF_SESSION_KEY);
        return usuarioCpfObj != null ? (String) usuarioCpfObj : null; // null ou outro valor padrão, se não estiver definido
    }

    // Construtor vazio
    public Usuario() {
        }
    
    // Construtor com argumentos
    public Usuario(String nome, String cpf, String email, String senha, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }
    
    // Métodos Getter e Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
