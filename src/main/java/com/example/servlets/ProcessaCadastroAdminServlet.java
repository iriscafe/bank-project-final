package com.example.servlets;

import com.example.dao.AdminDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcessaCadastroAdminServlet", urlPatterns = {"/ProcessaCadastroAdmin"})
public class ProcessaCadastroAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AdminDAO adminDAO = new AdminDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");

        boolean cadastroSucesso = adminDAO.inserirAdmin(nome, cpf, email, senha, telefone);

        if (cadastroSucesso) {
            request.getSession().setAttribute("sucessoCadastro", "Usuário cadastrado com sucesso!");
            response.sendRedirect(request.getContextPath() + "/PainelAdicionar.jsp");
        } else {
            request.getSession().setAttribute("erroCadastro", "Erro ao cadastrar usuário. Verifique os dados e tente novamente.");
            response.sendRedirect(request.getContextPath() + "/PainelAdicionar.jsp");
        }
    }
}
