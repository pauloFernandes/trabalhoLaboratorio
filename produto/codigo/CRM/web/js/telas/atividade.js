/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#cadastrar-convite").click(function() {
   carregaListaFuncionarios();
   $("div.popup-convite").css("display", "block");
   $("#shadow").css("display", "block");
});

$("#novo").click(function() {
    setEsquema(TelaAtividadeForm);
    carregarComboboxTipoAtividade();
	carregaComboboxClientes();
});

$("#pesquisa").click(function() {
    $("#popup-pesquisa").css("display", "block");
	$("#shadow").css("display", "block");
});

$("#salvar").click(function() {
    var codati    = $("#codati").val();
    var nomati    = $("#nomati").val();
    var codcli    = $("#codcli").val();
    var codtipati = $("#codtipati").val();
    var status    = $("#status:checked").val();
    var desati    = $("#desati").val();
    var datini    = $("#datini").val();
    var datfin    = $("#datfin").val();
    var obsandati = $("#obsandati").val();
	
	if (!codati) {
            codati = 0;
	}
	
	if (!codtipati) {
            codtipati = 0;
	}
        
        if (!codcli) {
            codcli = 0;
        }
        
	if (status) {
            status = "S";
	} else {
            status = "N"
	}
	
	$.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarAtividade",
        data: {
            TIPO_REQUISICAO: 3,
            CODATI: codati,
            NOMATI: nomati,
            CODCLI: codcli,
            CODTIPATI: codtipati,
            STATUS: status,
            DESATI: desati,
            DATINI: datini,
            DATFIN: datfin,
            OBSANDATI: obsandati
        },
        success: function(data) {
            alert("registro salvo com sucesso.");
        }
    });
	
});

$("#editar").click(function() {
    var tela = null
    if ($("#identificador-tela").html() == "tela-atividade-grid") {
        carregarForm();
    } else {
        carregarGrid();
    }
});

$("#excluir").click(function() {
    alert("excluir");
});

$("#button-pesquisar").click(function() {
	var codati = $("#pesquisa-codati").val();
	var codcli = $("#pesquisa-codcli").val();
	var nomati = $("#pesquisa-nomati").val();
	var status = $("#pesquisa-status:checked").val();
	var datini = $("#pesquisa-datini").val();
	var datfim = $("#pesquisa-datfin").val();
	
	if (!codati) {
		codati = 0;
	}
     
	if (!codcli) {
		codcli = 0;
	}
	 
	if (status) {
		status = "S";
	} else {
		status = "N"
	}
	
	requisicaoPesquisa(codati, codcli, nomati, status, datini, datfim)
	
});

$("#enviar-convites").click(function() {
    var codati     = $("tr.selected").find("td.codati").html();
    var descon     = $("#descon").val();
    var idtiplem   = $("#tiplem-1:checked").val() ? "1" : "2";
    var datinilem  = $("#datinilem").val();
    var convidados = $("#convidados").val();
   
    var strConvidados = "";
    for (var i = 0; i < convidados.length; i++) {
        strConvidados += convidados[i] + ",";
    }
    strConvidados = strConvidados.substr(0, strConvidados.length-1);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarAtividade",
        data: {
            TIPO_REQUISICAO: 5,
            CODATI: codati,
            DESCON: descon,
            IDTIPLEM: idtiplem,
            DATINILEM: datinilem,
            CONVIDADOS: strConvidados
        },
        success: function() {
            alert("Envio dado com sucesso.");
            $("#popup-convite").css("display", "none");
            $("#shadow").css("display", "none");
        }
    })        
});

function requisicaoPesquisa(codati, codcli, nomati, status, datini, datfim) {
	$.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarAtividade",
        data: {
            TIPO_REQUISICAO: 2,
            CODATI: codati,
			CODCLI: codcli,
			NOMATI: nomati,
			STATUS: status,
			DATINI: datini,
			DATFIM: datfim
        },
        success: function(data) {
            data = JSON.parse(data);
            $("#grid-atividades").html("");
            for (var i = 0; i < data.atividades.length; i++) {
                var linhaId    = i+1;
                var linhaClass = ((i+1)%2==1) ? "odd" : null;
                if (i === 0) linhaClass += " selected";
                var linha = "<tr id='"+linhaId+"' class='"+linhaClass+"'>" + 
                            "    <td class='codati'>" + data.atividades[i].codati + "</td>" + 
                            "    <td class='nomati'>" + data.atividades[i].nomati + "</td>" + 
                            "    <td class='codtipati' id='" + data.atividades[i].codtipati + "'>" + data.atividades[i].nomtipati + "</td>" + 
                            "    <td class='status'>" + ((data.atividades[i].status === "S") ? "Concluído" : "Em andamento") + "</td>" + 
                            "    <td class='datini'>" + data.atividades[i].datini + "</td>" + 
                            "    <td class='datfin'>" + data.atividades[i].datfin + "</td>" + 
                            "    <div style='display: none' class='obsandati' >" + data.atividades[i].obsandati + "</div>" + 
                            "    <div style='display: none' class='desati' >" + data.atividades[i].desati+ "</div>" + 
                            "</tr>";
                
                linha = linha.replace("undefined", " - ");
                linha = linha.replace("undefined", " - ");
                $("#grid-atividades").append(linha);
            }
            
        }
    });
}

function carregarComboboxTipoAtividade(tipoSeleciondo) {
    if (tipoSeleciondo == undefined) {
        tipoSeleciondo = 0;
    }
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarAtividade",
        data: {
            TIPO_REQUISICAO: 1
        },
        success: function(data) {
            data = JSON.parse(data);

            var options = "";
            
            for (var i = 0; i < data.tipos.length; i++) {
                if (tipoSeleciondo == data.tipos[i].codtipati) {
                    options += "<option value='" + data.tipos[i].codtipati +"' selected>" + data.tipos[i].nomtipati +"</option>";
                } else {
                    options += "<option value='" + data.tipos[i].codtipati +"'>" + data.tipos[i].nomtipati +"</option>";
                }
            }
            
            $("#codtipati").html(options);
        }
    });
}

function carregaComboboxClientes(clienteSelecionado) {
    if (clienteSelecionado == undefined) {
        clienteSelecionado = 0;
    }
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarAtividade",
        data: {
            TIPO_REQUISICAO: 4
        },
        success: function(data) {
            data = JSON.parse(data);
            console.log(data)
            var options = "";
            
            for (var i = 0; i < data.clientes.length; i++) {
                if (clienteSelecionado == data.clientes[i].codcli) {
                    options += "<option value='" + data.clientes[i].codcli +"' selected>" + data.clientes[i].razsoc +"</option>";
                } else {
                    options += "<option value='" + data.clientes[i].codcli +"'>" + data.clientes[i].razsoc +"</option>";
                }
            }
            
            $("#codcli").html(options);
        }
    });
}

function carregarForm() {
    linha = $("tr.selected");
    setEsquema(TelaAtividadeForm);
	
    carregarComboboxTipoAtividade(linha.find("td.codtipati").attr("id"));
    carregaComboboxClientes(linha.find("td.codcli").attr("id"));
    $("#codati").val(linha.find("td.codati").html());
	$("#nomati").val(linha.find("td.nomati").html());
	
	if (linha.find("td.status").html() == "Concluído") {
		$("#status").attr("checked", "checked");
	}
	
	$("#datini").val(linha.find("td.datini").html());
	$("#datfin").val(linha.find("td.datfin").html());
	$("#obsandati").val(linha.attr("obsandati"));
	$("#desati").val(linha.attr("desati"));
}

function carregarGrid() {
    setEsquema(TelaAtividadeGrid);
    inicializaAtividadeGrid();
}

function carregaListaFuncionarios() {
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
                option = "<option value='"+data.vendedores[i].codfun+"'>" + data.vendedores[i].nomusu + "</option>";
                
                $("#convidados").append(option);
            }
        }
    });
}