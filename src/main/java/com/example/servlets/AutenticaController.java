package com.example.servlets;

import com.example.dao.UsuarioDAO;
import com.example.model.Usuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AutenticaController", urlPatterns = { "/autentica" })
public class AutenticaController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if ("Logout".equals(acao)) {
            encerrarSessao(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void encerrarSessao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");

        if (verificarCredenciais(email, senha)) {
            UsuarioDAO userDAO = new UsuarioDAO();
            int idUsuario = userDAO.getIdUsuarioByEmail(email);
            String cpfUsuario = userDAO.getCPFByEmail(email);

            Usuario.setIdUsuario(request.getSession(), idUsuario);
            Usuario.setCPFUsuario(request.getSession(), cpfUsuario);

            if (verificarAdmin(email)) {
                response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("user.jsp");
            }
        } else {
            request.removeAttribute("mensagemErro");
            request.setAttribute("mensagemErro", "Senha incorreta. Tente novamente.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    public String getEmailUsuarioLogado(HttpSession session) {
        if (session != null) {
            Object emailObj = session.getAttribute("email");
            if (emailObj instanceof String) {
                return (String) emailObj;
            }
        }
        return null;
    }

    private boolean verificarCredenciais(String email, String senha) {
        UsuarioDAO userDAO = new UsuarioDAO();
        String senhaDoBanco = userDAO.getSenhaByEmail(email);
        return senhaDoBanco != null && senhaDoBanco.equals(senha);
    }

    private boolean verificarAdmin(String email) {
        return email != null && email.endsWith("@admin.com");
    }
}