/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    
    /**
     * Método para validação de usuario no login.
     */
    $("#acessar").click(function() {
        login = $("#login").val();
        senha = $("#senha").val();
        
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/CRM/ServletTeste",
            data: {
                LOGIN: login,
                SENHA: senha
            },
            success: function(data) {
                alert(data);
            }
        });
    });
    
    
    /**
     * Função para criação de novos usuários no sistema.
     */
    $("#cadatrar").click(function() {
        cadastrarFuncionario();
    });
    
    
    /**
     * Metodos usados para intercalar os tipos de cadastro de empresa (nova empresa ou empresa existente).
     */
    $("#radio-nova-empresa").click(function() {
        $("#nova-empresa").css("display", "none");
        $("#associar-empresa").css("display", "block");
    });
    $("#radio-associar-empresa").click(function() {
        $("#nova-empresa").css("display", "block");
        $("#associar-empresa").css("display", "none");
    });
    
});

function cadastrarFuncionario() {
    var nome  = $("#nome").val();
    var login = $("#login").val();
    var senha = $("#senha").val();

    var tipoCadastro       = null;
    var empresaSelecionada = 0;
    var inscjur            = null;
    var nomfan             = null;
    var razsoc             = null

    if ($("#radio-associar-empresa:checked").attr("name")) {
        tipoCadastro = 1;
        empresaSelecionada = $("#empresa-selecionada").val();
    }

    if ($("#radio-nova-empresa:checked").attr("name")) {
        tipoCadastro       = 2;
        inscjur      = $("#inscjur").val();
        nomfan       = $("#inscjur").val();
        razsoc       = $("#razsoc").val();
    }
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/ServletTeste",
        data: {
            NOME: nome,
            LOGIN: login,
            SENHA: senha,
            TIPOCADASTRO: tipoCadastro,
            EMPRESASELECIONADA: empresaSelecionada,
            INSCJUR: inscjur,
            NOMFAN: nomfan,
            RAZSOC: razsoc
        },
        success: function(data) {
            alert(data);
        }
    });
    
}