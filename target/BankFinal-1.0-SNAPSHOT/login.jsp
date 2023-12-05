<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-br">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <title>Login</title>
    </head>

    <body>
        <div class="container">

            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6 bg-light p-5">
                        <img src="https://media.discordapp.net/attachments/963533169708134463/1166426317890261072/OIG.png"
                            alt="Imagem do Banco" class="img-fluid">
                    </div>
                    <div class="col-md-6 d-flex align-items-center justify-content-center">
                        <div class="w-75 text-center">
                            <img src="https://media.discordapp.net/attachments/963533169708134463/1166479547840090203/OIG.51t5Dx_P0SKgz-removebg-preview.png"
                                alt="Sua Descrição" class="img-fluid mb-3">
                            <h2>Login</h2>

                            <% String msgError=(String) request.getAttribute("msgError"); if ((msgError !=null) &&
                                (!msgError.isEmpty())) {%>
                                <div class="alert alert-danger" role="alert">
                                    <%= msgError%>
                                </div>
                                <% }%>
                                    <form name="formLogin" action="autentica" method="POST" class="form">
                                        <div class="form-group">
                                            <label for="email">E-mail:</label>
                                            <input type="text" class="form-control" name="email" id="email"
                                                placeholder="Insira o email" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="senha">Senha:</label>
                                            <input type="password" class="form-control" name="senha" id="senha" placeholder="Insira a senha" required>
                                            <input type="radio" name="adm" id="adm" value="adm" /> Administrador <br>
                                            <% if (request.getAttribute("mensagemErro") != null) { %>
                                                <div class="alert alert-danger" role="alert">
                                                    <%= request.getAttribute("mensagemErro") %>
                                                </div>
                                            <% } %>
                                        </div>
                                        <button type="submit" class="btn btn-danger btn-block">Entrar</button>
                                    </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="footer fixed-bottom">
            &copy; 2023 Sistema Bancário
        </div>
        <script>
            function mostrarNotificacao() {
                var urlParams = new URLSearchParams(window.location.search);
                if (urlParams.has('cadastroSucesso')) {
                    alert('Cadastro realizado com sucesso!');
                }
            }
            window.onload = mostrarNotificacao;
        </script>
        <script src="scripts/script.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="scripts/bootstrap.min.css"></script>
    </body>

    </html>