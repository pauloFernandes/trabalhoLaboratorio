$(document).ready(function() {
   $("#salvar").click(function() {
        var codigo         = $("#codigo").val();
        var nome           = $("#nome").val();
        var login          = $("#login").val();
        var senha          = $("#senha").val();
        var confirmarSenha = $("#confirmar-senha").val();
        var empresa        = $("#lista-empresa").val();

        var obj = {
            CODIGO: codigo,
            NOME: nome,
            LOGIN: login,
            SENHA: senha,
            CONFIRMAR_SENHA: confirmarSenha,
            EMPRESA: empresa,
            TIPO_REQUISICAO: 2
        };

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/CRM/PerfilServlet",
            data: obj,
            success: function(data) {
                alert(data);
            }
        });
    });

    $("#excluir").click(function() {
        
        var codigo = $("#codigo").val();
        var confirmacao = confirm("Deseja mesmo excluir o usuário? Você perderá o acesso ao sistema.");
        if (confirmacao) {
            $.ajax({
            type: "POST",
            url: "http://localhost:8080/CRM/PerfilServlet",
            data: {
                CODIGO: codigo,
                TIPO_REQUISICAO: 3
            },
            success: function(data) {
                window.location = "http://localhost:8080/CRM";
            }
        });
        }
    }); 
});