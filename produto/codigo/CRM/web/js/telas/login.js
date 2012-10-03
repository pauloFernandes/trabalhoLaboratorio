/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {    
    listaEmpresas();
    
    /**
     * Método para validação de usuario no login.
     */
    $("#entrar").click(function() {
        login();
    });
    
    $("#radio-nova-empresa").click(function(){
        $("#nova-empresa").css("display", "block");
        $("#associar-empresa").css("display", "none");
    });
    $("#radio-associar-empresa").click(function(){
        $("#associar-empresa").css("display", "block");
        $("#nova-empresa").css("display", "none");
    });
        
    /**
     * Função para criação de novos usuários no sistema.
     */
    $("#cadatrar").click(function() {
        cadastrarFuncionario();
    });
    
    $("#teste").click(function() {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/CRM/ServletTeste",
            data: {
                TIPO_REQUISICAO: 1
            },
            success: function(data) {
                console.log(data)
            }
        });
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
        tipoCadastro = 2;
        inscjur      = $("#inscjur").val();
        nomfan       = $("#nomfan").val();
        razsoc       = $("#razsoc").val();
    }
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/LoginServlet",
        data: {
            TIPO_REQUISICAO: "2",
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

function listaEmpresas() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/LoginServlet",
        data: {
            TIPO_REQUISICAO: "3"
        },
        success: function(data) {
            setListaEmpresas(data);
        }
    });
}

function setListaEmpresas(data) {
    var select = $("#empresa-selecionada");
    
    var empresas = data.split("####");
    
    for (var i = 0; i < empresas.length - 1; i++) {
        var values = empresas[i].   split("__#__");
        $(select).append("<option value='" + values[0] + "'>"+values[1]+"</option>");
    }
}

function login() {
    var login = $("#log").val();
    var senha = $("#pass").val();
        
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/LoginServlet",
        data: {
            TIPO_REQUISICAO: "1",
            LOGIN: login,
            SENHA: senha
        },
        success: function(data) {
            var usuarioValido = Boolean(data);
            if (usuarioValido) {
                window.location = "http://localhost:8080/CRM/html/base.jsp";
            } else {
                alert("Usuário ou senha inválidos");
            }
        }
    });
}