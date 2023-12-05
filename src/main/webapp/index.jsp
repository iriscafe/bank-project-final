<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Favela Bank</title>
    <link rel="stylesheet" href="style/bootstrap.css">
    <link href="style/style.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand text-center ms-2" href="index.jsp">
                <img src="https://media.discordapp.net/attachments/963533169708134463/1166479547840090203/OIG.51t5Dx_P0SKgz-removebg-preview.png" width="50px">
            </a>
            <div class="cadastro_entrar">
                <a href="cadastro.jsp" class="btn btn-primary">Abra já a sua conta</a>
                <a href="#sobre" class="btn btn-secondary">Sobre nós</a>                
                <a href="login.jsp" class="btn btn-secondary">Entrar</a>
            </div>
        </div>
    </nav>
    
    <div class="col">
        <h2>Mais que um banco, uma comunidade</h2>
        <p> Bem-vindo ao <b>FavelaBank</b>, um inovador banco comunitário que está transformando a forma como as
            comunidades de baixa renda lidam com suas finanças.
        </p>
        <a href="cadastro.jsp" class="btn btn-primary">Abra já a sua conta</a>
    </div>
    <div id="img_chamada">
        <img src="assets/chamada.png" alt="">
    </div>

    <div id="sobre" class="p-4">
        <div class="row">
            <!-- Texto à esquerda -->
            <div class="col-md-6 text-center text-md-left">
                <h2>Sobre o Banco</h2>
                <p>
                    Bem-vindo ao FavelaBank, um inovador banco comunitário que está transformando a forma como as
                    comunidades de baixa renda lidam com suas finanças. Com um compromisso dedicado a promover a inclusão
                    financeira e empoderar indivíduos em favelas e áreas desfavorecidas, o FavelaBank está redefinindo o
                    significado
                    de bancos tradicionais. Nossa missão é criar oportunidades financeiras acessíveis, fomentar o crescimento
                    econômico
                    local e capacitar os residentes de favelas a construir um futuro financeiro mais brilhante. Descubra como o
                    FavelaBank está se tornando um catalisador de mudanças positivas nas comunidades que atendemos e saiba como
                    você
                    pode fazer parte dessa transformação. Junte-se a nós enquanto exploramos as possibilidades de um amanhã
                    financeiro
                    mais igualitário e próspero.
                </p>
            </div>

            <!-- Imagem à direita -->
            <div class="col-md-6 d-flex justify-content-center">
                <img src="https://emprestimosim.com.br/wp-content/uploads/2021/04/Blog_imagem_2-3-1024x681.jpg" alt="Imagem Sobre" class="img-fluid">
            </div>
            
        </div>
    </div>

    <div class="footer fixed-bottom">
        &copy; 2023 Sistema Bancário
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
