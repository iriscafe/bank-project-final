package com.example.servlets;

import com.example.dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcessaCadastroServlet", urlPatterns = {"/ProcessaCadastroServlet"})
public class ProcessaCadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");

        boolean cadastroSucesso = usuarioDAO.inserirUsuario(nome, cpf, email, senha, telefone);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (cadastroSucesso) {
                response.sendRedirect("login.jsp?cadastroSucesso=true");
            } else {
                out.println("<h2>Falha no cadastro</h2>");
                out.println("<p>Desculpe, ocorreu um erro durante o cadastro.</p>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }
}