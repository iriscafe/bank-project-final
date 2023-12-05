package com.example.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuario"})
public class UsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // Implemente a lógica para recuperar dados do banco e exibir na página
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // Implemente a lógica para adicionar/atualizar dados no banco
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        // Implemente a lógica para excluir dados do banco
    }
}
