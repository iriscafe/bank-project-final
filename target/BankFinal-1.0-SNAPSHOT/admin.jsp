<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Usuario" %>
<%@ page import="com.example.dao.AdminDAO" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Painel do Administrador</title>
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
        <h2>Painel do Administrador</h2>

        <!-- Opções CRUD para usuários -->
        <div class="row mt-4">
            <div class="col-md-3">
                <h4>Opções:</h4>
                <ul class="list-group">
                    <li class="list-group-item"><a href="#" data-alvo="painelVisualizar">Visualizar Usuários</a></li>
                    <li class="list-group-item"><a href="#" data-alvo="painelAdicionar">Adicionar Usuário</a></li>
                    <li class="list-group-item"><a href="#" data-alvo="painelEditar">Editar Usuário</a></li>
                    <li class="list-group-item"><a href="#" data-alvo="painelExcluir">Excluir Usuário</a></li>

                </ul>
            </div>
            <div class="col-md-9">
                <!-- Conteúdo dinâmico aqui -->
                <div id="painelVisualizar" class="painel" style="display:block;">
                    <!-- Conteúdo do painel de visualização -->
                    <h4>Visualizar Usuários</h4>
            
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>CPF</th>
                                <th>Email</th>
                                <th>Telefone</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                AdminDAO adminDAO = new AdminDAO();
                                List<Usuario> usuarios = adminDAO.listarUsuarios();
                                for (Usuario usuario : usuarios) {
                            %>
                                <tr>
                                    <td><%= usuario.getNome() %></td>
                                    <td><%= usuario.getCpf() %></td>
                                    <td><%= usuario.getEmail() %></td>
                                    <td><%= usuario.getTelefone() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                
                <div id="painelAdicionar" class="painel" style="display:none;">
                    <h4>Adicionar Usuário</h4>
                    <%-- Verifica se há mensagem de sucesso --%>
                    <% if (request.getAttribute("sucessoCadastro") != null) { %>
                        <div class="alert alert-success" role="alert">
                            <%= request.getAttribute("sucessoCadastro") %>
                        </div>
                    <% } %>
                
                    <%-- Verifica se há mensagem de erro --%>
                    <% if (request.getAttribute("erroCadastro") != null) { %>
                        <div class="alert alert-danger" role="alert">
                            <%= request.getAttribute("erroCadastro") %>
                        </div>
                    <% } %>
                    <form action="ProcessaCadastroAdminServlet" method="post">
                        <div class="form-group">
                            <label for="nome">Nome:</label>
                            <input type="text" class="form-control" name="nome" required>
                        </div>
                        <div class="form-group">
                            <label for="cpf">CPF:</label>
                            <input type="text" class="form-control" name="cpf" required>
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail:</label>
                            <input type="email" class="form-control" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="senha">Senha:</label>
                            <input type="password" class="form-control" name="senha" required>
                        </div>
                        <div class="form-group">
                            <label for="telefone">Telefone:</label>
                            <input type="text" class="form-control" name="telefone" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Adicionar Usuário</button>
                    </form>
                </div>
                
                <div id="painelEditar" class="painel" style="display:none;">
                    <!-- Conteúdo do painel de edição -->
                    <h4>Editar Usuário</h4>
                
                    <form action="ProcessaEditarUsuario" method="post">
                        <div class="form-group">
                            <label for="cpfEditar">CPF do Usuário:</label>
                            <input type="text" class="form-control" name="cpfEditar" required>
                        </div>
                        <div class="form-group">
                            <label for="campoEditar">Campo a Editar:</label>
                            <select class="form-control" name="campoEditar" required>
                                <option value="nome">Nome</option>
                                <option value="email">E-mail</option>
                                <!-- Adicione outras opções para campos adicionais -->
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="novoValor">Novo Valor:</label>
                            <input type="text" class="form-control" name="novoValor" required>
                        </div>
                
                        <button type="submit" class="btn btn-primary">Editar Usuário</button>
                    </form>
                </div>
                
                <div id="painelExcluir" class="painel" style="display:none;">
                    <!-- Conteúdo do painel de exclusão -->
                    <h4>Excluir Usuário</h4>
            
                    <form action="ProcessaExcluirUsuario" method="post">
                        <div class="form-group">
                            <label for="cpfExcluir">CPF do Usuário:</label>
                            <input type="text" class="form-control" name="cpfExcluir" required>
                        </div>
                        <button type="submit" class="btn btn-danger">Excluir Usuário</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="footer fixed-bottom">
        &copy; 2023 Sistema Bancário
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function () {
            $(".list-group-item a").click(function (event) {
                event.preventDefault(); // Impede o comportamento padrão do link
                
                var painelAlvo = $(this).data("alvo"); // Obtém o valor do atributo 'data-alvo'
    
                // Esconder todos os painéis
                $(".painel").hide();
    
                // Mostrar o painel selecionado
                $("#" + painelAlvo).show();
            });
        });
    </script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</body>
</html>
