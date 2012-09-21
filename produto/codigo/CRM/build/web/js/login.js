/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#button-cadastrar-empresa").click(function() {
        $("#cadastrar-empresa").css("display", "block");
        $("#escolher-empresa").css("display", "none");
    });
    
    $("#button-associar-empresa").click(function(){
        $("#escolher-empresa").css("display", "block");
        $("#cadastrar-empresa").css("display", "none");
    });
    
    $("#cadastrar").click(function(){
        var nome         = $("#nome").val();
        var login        = $("#login").val();
        var senha        = $("#senha").val();
        var novaEmpresa  = $("#nova-empresa").val();
        var listaEmpresa = $("#lista-empresa").val();
        var empresa      = "";
        
        if (novaEmpresa != "") {
            empresa = novaEmpresa;
        } else {
            emrpesa = listaEmpresa;
        }
        
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/CRM/ServletTeste",
            data: {
                NOME: "",
                LOGIN: "",
                SENHA: "",
                EMPRESA: ""
            },
            success: function(data) {
                alert("fuckup");
            }
        });
    });
    
});

/*
 *$.ajax({
    type: "POST",
    url: "requests_catalogo.php",
    data: {
        ITENS: Itens,
        CDFILIAL: cdFilial,
        CDOPERADOR: cdoperador,
        NRORG: nrorg,
        REQUEST_CODE: 1
    },
    success: function(data) {
        console.log(data);
        alert(data);
        window.location.reload();
        // window.location.reload();
    }
});
 **/