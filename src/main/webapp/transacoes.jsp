<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Transacao" %>

<!DOCTYPE html>
<html lang="pt-br"> 
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Área do Usuário - Transações</title>
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
        <h2>Área do Usuário - Transações</h2>

        <!-- Tabela para exibir transações -->
        <table class="table">
            <thead>
                <tr>
                    <th>Tipo</th>
                    <th>Valor</th>
                    <th>Data</th>
                    <th>CPF</th>
                </tr>
            </thead>
            <tbody>
                <!-- Use a expressão de script JSP para acessar a lista de transações da solicitação -->
                <c:forEach var="transacao" items="${historicoTransacoes}">
                    <tr>
                        <td>${transacao.tipo}</td>
                        <td>${transacao.valor}</td>
                        <td>${transacao.data}</td>
                        <td>${transacao.cpf}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="footer fixed-bottom">
        &copy; 2023 Sistema Bancário
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
