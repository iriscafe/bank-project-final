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

@WebServlet(name = "ProcessaEditarUsuario", urlPatterns = { "/ProcessaEditarUsuario" })
public class ProcessaEditarUsuario extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        String novoNome = request.getParameter("novoNome");
        String novoEmail = request.getParameter("novoEmail");

        if (cpf != null && (novoNome != null || novoEmail != null)) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.getUsuarioByCPF(cpf);

            if (usuario != null) {
                if (novoNome != null) {
                    usuario.setNome(novoNome);
                    boolean sucesso = usuarioDAO.editarNomeUsuario(cpf, novoNome);
                    if (sucesso) {
                        response.sendRedirect("admin.jsp");
                        return;
                    } else {
                        request.setAttribute("mensagemErro", "Falha na atualização do nome do usuário.");
                    }
                } else if (novoEmail != null) {
                    usuario.setEmail(novoEmail);
                    boolean sucesso = usuarioDAO.editarEmailUsuario(cpf, novoEmail);
                    if (sucesso) {
                        response.sendRedirect("admin.jsp");
                        return;
                    } else {
                        request.setAttribute("mensagemErro", "Falha na atualização do e-mail do usuário.");
                    }
                }
            } else {
                request.setAttribute("mensagemErro", "Usuário não encontrado.");
            }
        } else {
            request.setAttribute("mensagemErro", "Parâmetros ausentes.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
        dispatcher.forward(request, response);
    }

}
