function checkUserType() {
    var isAdmin = document.getElementById('adm').checked;

    if (isAdmin) {
        window.location.href = 'admin.jsp';
    } else {
        window.location.href = 'user.jsp';
    }

    return false;
}
function mostrarNotificacao() {
    // Verifica se o parâmetro 'cadastroSucesso' está presente na URL
    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('cadastroSucesso')) {
        // Adapte a notificação conforme necessário
        alert('Cadastro realizado com sucesso!'); // Você pode usar bibliotecas ou criar um balão customizado aqui
    }
}

window.onload = mostrarNotificacao;