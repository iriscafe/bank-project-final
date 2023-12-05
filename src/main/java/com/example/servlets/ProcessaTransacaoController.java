package com.example.servlets;

import com.example.dao.TransacaoDAO;
import com.example.model.Transacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProcessaTransacaoController", urlPatterns = { "/ProcessaTransacao" })
public class ProcessaTransacaoController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String valorStr = request.getParameter("valor");
        valorStr = valorStr.replace(",", ".");

        try {
            double valor = Double.parseDouble(valorStr);

            // Chame a função para realizar o depósito ou saque
            if ("depositar".equals(acao)) {
                realizarDeposito(request, response, valor);
            } else if ("sacar".equals(acao)) {
                realizarSaque(request, response, valor);
            } else {
                // Ação desconhecida
                response.sendRedirect("user.jsp");
            }
        } catch (NumberFormatException e) {
            // Lide com o erro de formato inválido
            e.printStackTrace(); // ou envie uma resposta de erro
        }
    }

    private void realizarDeposito(HttpServletRequest request, HttpServletResponse response, double valor)
            throws ServletException, IOException {
        // Obter dados do usuário (pode precisar ajustar conforme sua lógica de autenticação)
        String cpfUsuario = "obtenhaDeSessaoOuCasoNecessario";
        String tipo = "Depósito";

        // Criar objeto Transacao
        Transacao transacao = new Transacao(cpfUsuario, tipo, valor);

        // Realizar depósito
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        boolean sucesso = transacaoDAO.realizarTransacao(transacao);

        if (sucesso) {
            // Atualizar saldo no banco ou realizar outras operações necessárias
            // Redirecionar para a página do usuário com uma mensagem de sucesso
            response.sendRedirect("user.jsp?depositoSucesso=true");
        } else {
            // Tratar falha no depósito
            response.sendRedirect("user.jsp?depositoSucesso=false");
        }
    }

    private void realizarSaque(HttpServletRequest request, HttpServletResponse response, double valor)
            throws ServletException, IOException {
        // Obter dados do usuário (pode precisar ajustar conforme sua lógica de autenticação)
        String cpfUsuario = "obtenhaDeSessaoOuCasoNecessario";
        String tipo = "Saque";

        // Criar objeto Transacao
        Transacao transacao = new Transacao(cpfUsuario, tipo, valor);

        // Realizar saque
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        boolean sucesso = transacaoDAO.realizarTransacao(transacao);

        if (sucesso) {
            // Atualizar saldo no banco ou realizar outras operações necessárias
            // Redirecionar para a página do usuário com uma mensagem de sucesso
            response.sendRedirect("user.jsp?saqueSucesso=true");
        } else {
            // Tratar falha no saque
            response.sendRedirect("user.jsp?saqueSucesso=false");
        }
    }
}
