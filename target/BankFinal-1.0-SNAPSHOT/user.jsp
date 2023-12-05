<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Transacao" %>
<%@ page import="com.example.dao.TransacaoDAO" %>
<%@ page import="com.example.dao.UsuarioDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br"> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Área do Usuário</title>
    <link rel="stylesheet" href="style/bootstrap.css">
    <link href="style/style.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand text-center ms-2" href="index.html">
                <img src="https://media.discordapp.net/attachments/963533169708134463/1166479547840090203/OIG.51t5Dx_P0SKgz-removebg-preview.png" width="50px">
            </a>
            <div class="cadastro_entrar">
                <a href="autentica?acao=Logout" class="btn btn-secondary">Sair</a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <h2>Área do Usuário</h2>
        <!-- Opções CRUD para dinheiro -->
        <div class="row mt-4">
            <div class="col-md-3">
                <h4>Opções:</h4>
                <ul class="list-group">
                    <li class="list-group-item"><a href="#" id="linkVerTransacoes">Ver Transações</a></li>
                </ul>
            </div>
            <div class="col-md-9">
                <!-- Conteúdo dinâmico aqui -->
                <div id="painelTransacao" class="painel" style="display:none;">
                    <h4>Visualizar Transacoes</h4>
                    <table class="table" id="tabelaTransacoes">
                        <!-- Cabeçalho da tabela -->
                        <thead>
                            <tr>
                                <th>Tipo</th>
                                <th>Valor</th>
                                <th>Data</th>
                                <th>CPF</th>
                            </tr>
                        </thead>
                        <!-- Corpo da tabela preenchido dinamicamente com Java -->
                        <tbody id="corpoTabelaTransacoes">
                            <%
                                UsuarioDAO usuarioDAO = new UsuarioDAO();
                                TransacaoDAO transacaoDAO = new TransacaoDAO();
                                String emailString = "teste@teste.com";
                                String cpf = usuarioDAO.getCPFByEmail(emailString);
                                List<Transacao> historicoTransacoes = transacaoDAO.obterHistorico(cpf);

                                for (Transacao transacao : historicoTransacoes) {
                            %>
                            <tr>
                                <td><%= transacao.getTipo() %></td>
                                <td><%= transacao.getValor() %></td>
                                <td><%= transacao.getData() %></td>
                                <td><%= transacao.getCPF() %></td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <!-- Formulário para depósito -->
                <form action="ProcessaTransacao?acao=depositar" method="post">
                    <div class="mb-3">
                        <label for="valor">Valor:</label>
                        <input type="text" class="form-control" name="valor" id="valor" placeholder="Informe o valor" required>
                    </div>
                    <button type="submit" class="btn btn-success">Depositar</button>
                </form>
                <!-- Formulário para saque -->
                <form action="ProcessaTransacao?acao=sacar" method="post">
                    <div class="mb-3">
                        <label for="valorSaque">Valor:</label>
                        <input type="text" class="form-control" name="valorSaque" id="valorSaque" placeholder="Informe o valor" required>
                    </div>
                    <button type="submit" class="btn btn-warning">Sacar</button>
                </form>
            </div>
        </div>
    </div>

    <div class="footer fixed-bottom">
        &copy; 2023 Sistema Bancário
    </div>
    <script>
        function toggleTabelaTransacoes() {
            var tabela = document.getElementById("painelTransacao");
            tabela.style.display = tabela.style.display === "none" ? "block" : "none";
        }
        document.getElementById("linkVerTransacoes").addEventListener("click", function (event) {
            event.preventDefault();
            toggleTabelaTransacoes();
        });
    </script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
