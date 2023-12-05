package com.example.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.TransacaoDAO;
import com.example.model.Usuario;

@WebServlet(name = "ProcessaTransacaoController", urlPatterns = { "/ProcessaTransacao" })
public class ProcessaTransacaoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cpf = Usuario.getCPFUsuario(session);

        String acao = request.getParameter("acao");
        double valor = Double.parseDouble(request.getParameter("valor"));

        TransacaoDAO transacaoDAO = new TransacaoDAO();

        switch (acao) {
            case "depositar":
                transacaoDAO.inserirTransacao("Depósito", valor, cpf);
                break;
            case "sacar":
                transacaoDAO.inserirTransacao("Saque", -valor, cpf);
                break;
            default:
                break;
        }

        response.sendRedirect("user.jsp");
    }
}