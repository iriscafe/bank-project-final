package com.example.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.TransacaoDAO;
import com.example.dao.UsuarioDAO;
import com.example.model.Transacao;

@WebServlet(name = "TransacaoServlet", urlPatterns = {"/Transacao"})
public class TransacaoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    TransacaoDAO transacaoDAO = new TransacaoDAO();

    String emailString = "teste@teste.com";
    String cpf = usuarioDAO.getCPFByEmail(emailString);
    List<Transacao> historicoTransacoes = transacaoDAO.obterHistorico(cpf);

    StringBuilder tabelaHTML = new StringBuilder();
    tabelaHTML.append("<thead><tr><th>Tipo</th><th>Valor</th><th>Data</th><th>CPF</th></tr></thead><tbody>");

    for (Transacao transacao : historicoTransacoes) {
        tabelaHTML.append("<tr>");
        tabelaHTML.append("<td>").append(transacao.getTipo()).append("</td>");
        tabelaHTML.append("<td>").append(transacao.getValor()).append("</td>");
        tabelaHTML.append("<td>").append(transacao.getData()).append("</td>");
        tabelaHTML.append("<td>").append(transacao.getCPF()).append("</td>");
        tabelaHTML.append("</tr>");
    }

    tabelaHTML.append("</tbody>");

    response.getWriter().write(tabelaHTML.toString());
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
    }
}
