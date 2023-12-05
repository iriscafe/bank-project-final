package com.example.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.example.dao.UsuarioDAO;
import com.example.model.Usuario;

@WebServlet(name = "ProcessaEditarUsuario", urlPatterns = {"/ProcessaEditarUsuario"})
public class ProcessaEditarUsuario extends HttpServlet {
    // ...

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpfEditar = request.getParameter("cpfEditar");

        // Buscar detalhes do usuário pelo CPF
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.getUsuarioByCPF(cpfEditar);

        if (usuario != null) {
            // Exibir os detalhes do usuário no JSP
            request.setAttribute("nomeUsuario", usuario.getNome());
            request.setAttribute("emailUsuario", usuario.getEmail());
            request.setAttribute("cpfUsuario", usuario.getCpf());

            // Mostrar o formulário de edição
            request.setAttribute("detalhesUsuario", true);
        } else {
            // Usuário não encontrado, mostrar mensagem de erro
            request.setAttribute("mensagemErro", "Usuário não encontrado.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Admin.jsp");
        dispatcher.forward(request, response);
    }
}
