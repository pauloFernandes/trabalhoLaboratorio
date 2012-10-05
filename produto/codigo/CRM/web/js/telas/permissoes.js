$("#pesquisar").click(function() {
    $("#shadow").css("display", "block");
    $("#popup-pesquisa").css("display", "block");
});

$("#aprovar-vinculo").click(function() {
    var codusu   = $("tr.selected").find("td.nome").attr("id");
    var confirma = confirm("Confirma Aprovação de Vínculo?");
    
    if (confirma) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/CRM/PermissoesServlet",
            data: {
                CODUSU: codusu,
                TIPO_REQUISICAO: 3
            },
            success: function() {
                alert("Permissão concedida com sucesso.");
                $("tbody").html("");
                inicializaGridPermissao();
            }
        });
    }
});

$("#reprovar-vinculo").click(function() {
    alert("reprovar-vinculo");
});

$("#atribuir-permissao").click(function() {
    alert("atribuir-permissao");
});

$("#popup-pesquisar").click(function() {
    var nome        = $("#popup-nome").val();
    var datini      = $("#popup-datini").val();
    var datfim      = $("#popup-datfim").val();
    var solicitante = $("#popup-solicitante").attr("checked");
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/PermissoesServlet",
        data: {
            TIPO_REQUISICAO: 2,
            NOME: nome,
            DATINI: datini,
            DATFIM: datfim,
            SOLICITANTE: solicitante
        },
        success: function(data) {
            data = JSON.parse(data);
            $("tbody").html("");
            for (var i = 0; i < data.permissoes.length; i++) {
                var linhaId    = i+1;
                var linhaClass = ((i+1)%2==1) ? "odd" : null;
                if (i === 0) linhaClass += " selected";
                var linha = "<tr id='"+linhaId+"' class='"+linhaClass+"'>" + 
                            "   <td class='nome' id='" + data.permissoes[i].codusu + "'>" + data.permissoes[i].nome + "</td>" + 
                            "   <td class='tipo'>" + data.permissoes[i].tipo + "</td>" + 
                            "   <td class='solicitante'>" + data.permissoes[i].solicitante + "</td>" + 
                            "   <td class='datini'>" + data.permissoes[i].datini + "</td>" + 
                            "   <td class='datfim'>" + data.permissoes[i].datfim + "</td>" + 
                            "</tr>";
                linha = linha.replace("undefined", " - ");
                linha = linha.replace("undefined", " - ");
                $("tbody").append(linha);
            }
            
            $("#shadow").css("display", "none");
            $("div.popup").css("display", "none");
        }
    });
});