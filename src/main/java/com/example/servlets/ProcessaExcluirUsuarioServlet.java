package com.example.servlets;

import com.example.dao.AdminDAO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcessaExcluirUsuarioServlet", urlPatterns = {"/ProcessaExcluirUsuario"})
public class ProcessaExcluirUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final AdminDAO adminDAO = new AdminDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpfExcluir = request.getParameter("cpfExcluir");

        boolean exclusaoSucesso = adminDAO.excluirUsuario(cpfExcluir);

        if (exclusaoSucesso) {
            request.getSession().setAttribute("sucessoExclusao", "Usuário excluído com sucesso!");
        } else {
            request.getSession().setAttribute("erroExclusao", "Erro ao excluir usuário. Verifique o CPF e tente novamente.");
        }

        response.sendRedirect(request.getContextPath() + "/PainelExcluir.jsp");
    }
}
