/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#exibir-historico").click(function() {
    var cliente = $(".selected").find("td.nomfan").attr("id");
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarClientes",
        data: {
            TIPO_REQUISICAO: 2,
            CODCLI: cliente
        },
        success: function(data) {
            data = JSON.parse(data);
            $("#body-historico-vendedores").html("");
            for (var i = 0; i < data.vendedores.length; i++) {
                var linhaId    = i+1;
                var linhaClass = ((i+1)%2==1) ? "odd" : null;
                if (i === 0) linhaClass += " selected";
                var linha = "<tr id='"+linhaId+"' class='"+linhaClass+"'>" + 
                            "   <td class='nomven'>" + data.vendedores[i].nomven + "</td>" + 
                            "   <td class='datini'>" + data.vendedores[i].datini+ "</td>" + 
                            "   <td class='datfim'>" + data.vendedores[i].datfim + "</td>" + 
                            "</tr>";
                linha = linha.replace("undefined", " - ");
                linha = linha.replace("undefined", " - ");
                $("#body-historico-vendedores").append(linha);
            }
            
            $("#shadow").css("display", "block");
            $("#popup-historico-vendedores").css("display", "block");
        }
    });
});

$("#novo").click(function() {
    setEsquema(TelaClienteForm);
    carregaComboboxVendedores();
});

$("#pesquisar").click(function() {
    $("#shadow").css("display", "block");
    $("#popup-pesquisa").css("display", "block");
});

$("#salvar").click(function() {
    var codcli   = $("#codcli").val();
    var nomfan   = $("#nomfan").val();
    var razsoc   = $("#razsoc").val();
    var telemp   = $("#telemp").val();
    var celemp   = $("#celemp").val();
    var insjur   = $("#insjur").val();
    var vendedor = $("#lista-vendedores").val();
    
    if (!codcli) {
        codcli = 0;
    }
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarClientes",
        data: {
            TIPO_REQUISICAO: 5,
            CODCLI: codcli,
            NOMFAN: nomfan,
            RAZSOC: razsoc,
            INSJUR: insjur,
            TELEMP: telemp,
            CELEMP: celemp,
            VENDEDOR: vendedor
        },
        success: function() {
            alert("Registro salvo com sucesso.");
        }
    });
});

$("#editar").click(function() {
    var tela = null
    if ($("#identificador-tela").html() == "tela-cliente-grid") {
        carregarForm();
    } else {
        carregarGrid();
    }
});

$("#excluir").click(function() {
    alert("excluir");
});

$("#popup-botao-pesquisar").live("click", function(){
    var nomfan = $("#popup-nomfan").val();
    var razsoc = $("#popup-razsoc").val();
    var insjur = $("#popup-inscjur").val();
    var nomven = $("#popup-nomven").val();
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarClientes",
        data: {
            TIPO_REQUISICAO: 3,
            NOMFAN: nomfan,
            RAZSOC: razsoc,
            INSJUR: insjur,
            NOMVEN: nomven
        },
        success: function(data) {
            data = JSON.parse(data);
            
            $("tbody").html("");
            $("#shadow").css("display", "none");
            $("#popup-pesquisa").css("display", "none");
            for (var i = 0; i < data.clientes.length; i++) {
                var linhaId    = i+1;
                var linhaClass = ((i+1)%2==1) ? "odd" : null;
                if (i === 0) linhaClass += " selected";
                var linha = "<tr id='"+linhaId+"' class='"+linhaClass+"'>" + 
                            "   <td class='nomfan' id='" + data.clientes[i].codcli + "'>" + data.clientes[i].nomfan + "</td>" + 
                            "   <td class='razsoc'>" + data.clientes[i].razsoc + "</td>" + 
                            "   <td class='numinsjur'>" + data.clientes[i].numinsjur + "</td>" + 
                            "   <td class='nomven' codven='" + data.clientes[i].codven  + "'>" + data.clientes[i].nomven + "</td>" + 
                            "</tr>";
                linha = linha.replace("undefined", " - ");
                linha = linha.replace("undefined", " - ");
                $("tbody").append(linha);
            }
        }
    });
});

function carregarGrid() {
    setEsquema(TelaClienteGrid);
    inicializaClienteGrid();
}

function carregarForm() {
    linha = $("tr.selected");
    setEsquema(TelaClienteForm);
    carregaComboboxVendedores(linha.find("td.nomven").attr("codven"));
    $("#codcli").val(linha.find("td.nomfan").attr("id"));
    $("#nomfan").val(linha.find("td.nomfan").html());
    $("#razsoc").val(linha.find("td.razsoc").html());
    $("#insjur").val(linha.find("td.numinsjur").html());
    $("#telemp").val(linha.find("td.telemp").html());
    $("#celemp").val(linha.find("td.celemp").html());
}

function carregaComboboxVendedores(codfun) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarClientes",
        data: {
            TIPO_REQUISICAO: 4
        },
        success: function(data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.vendedores.length; i++) {
                var option = "";
                
                if (data.vendedores[i].codfun == codfun) {
                    option = "<option value='"+data.vendedores[i].codfun+"' selected>" + data.vendedores[i].nomusu + "</option>";
                } else {
                    option = "<option value='"+data.vendedores[i].codfun+"'>" + data.vendedores[i].nomusu + "</option>";
                }
                
                $("#lista-vendedores").append(option);
            }
        }
    });
}