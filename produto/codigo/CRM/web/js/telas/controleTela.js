function setEsquema(esquema) {
    $("#identificador-tela").html(esquema.idTela);
    $("#titulo").html(esquema.titulo);
    $("#botoes").html(esquema.botoes);
    $("#tela").html(esquema.tela);
    $("#scripts-temporarios").html(esquema.scripts);
}

ControleTela = function() {
    this.getEsquemaTela = function(esquema) {
        switch (esquema) {
            case 1: 
               return TelaPerfil;
               break;
			case 2: 
               return TelaPermissoes;
               break;
			case 3: 
               return TelaEmpresa;
               break;
			case 4: 
               return TelaClienteGrid;
               break;
			case 5: 
               return TelaAtividadeGrid;
               break;   
        }
    }
}

/**
 * Metodos para inicialização dos grids.
 */
function InicializaPerfil() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/PerfilServlet",
        data: {
            TIPO_REQUISICAO: 1
        },
        success: function(data) {
            data = JSON.parse(data);
            // Insersao dos dados do usuario.
            $("#codigo").val(data.codusu);
            $("#nome").val(data.nomusu);
            $("#login").val(data.logusu);
            
            // Construcao da lista de empresas que o usuário dispoe.
            var empresas = "<option id='0' value='0'>Nenhum Selecionado</option>";
            for (var i = 0; i < data.empresas.length; i++) {
                var aux = data.empresas;
                empresas += "<option id='" + aux[i].codemp + "' value='" + aux[i].codemp +"'>" + aux[i].razsoc + "</option>";
            }
            
            // Settando a lista no combobox da empresa e selecionando a empresa do funcionario, caso esta exista.
            $("#lista-empresa").html(empresas);
            $("#lista-empresa").val(data.empusu);
            
            // Verifica quais botoes estarao disponiveis para o usuario, de acordo com seu nível de permissão.
            controlePermissoesBotoes("perfil");
        }
    });
}

function inicializaGridPermissao() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/PermissoesServlet",
        data: {
            TIPO_REQUISICAO: 1
        },
        success: function(data) {
            data = JSON.parse(data);
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
            
            // Verifica quais botoes estarao disponiveis para o usuario, de acordo com seu nível de permissão.
            controlePermissoesBotoes("permissao");
        }
    });
}

function inicializarEmpresa() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarEmpresa",
        data: {
            TIPO_REQUISICAO: 1
        },
        success: function(data) {
            data = JSON.parse(data);     
            $("#codemp").val(data.codemp);
            $("#insjur").val(data.insjur);
            $("#nomfan").val(data.nomfan);
            $("#razsoc").val(data.razsoc);
            $("#telemp").val(data.telemp);
            $("#celemp").val(data.celemp);
            
            // Verifica quais botoes estarao disponiveis para o usuario, de acordo com seu nível de permissão.
            controlePermissoesBotoes("empresa");
        }
    });
}

function inicializaClienteGrid() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarClientes",
        data: {
            TIPO_REQUISICAO: 1
        },
        success: function(data) {
            data = JSON.parse(data);
            for (var i = 0; i < data.clientes.length; i++) {
                var linhaId    = i+1;
                var linhaClass = ((i+1)%2==1) ? "odd" : null;
                if (i === 0) linhaClass += " selected";
                var linha = "<tr id='"+linhaId+"' class='"+linhaClass+"'>" + 
                            "   <td class='nomfan' id='" + data.clientes[i].codcli + "'>" + data.clientes[i].nomfan + "</td>" + 
                            "   <td class='razsoc'>" + data.clientes[i].razsoc + "</td>" + 
                            "   <td class='numinsjur'>" + data.clientes[i].numinsjur + "</td>" + 
                            "   <td class='nomven' codven='" + data.clientes[i].codven  + "'>" + data.clientes[i].nomven + "</td>" + 
                            "   <td class='telemp'>" + data.clientes[i].telemp + "</td>" + 
                            "   <td class='celemp'>" + data.clientes[i].celemp + "</td>" + 
                            "</tr>";
                linha = linha.replace("undefined", " - ");
                linha = linha.replace("undefined", " - ");
                $("tbody").append(linha);
            }
            
            // Verifica quais botoes estarao disponiveis para o usuario, de acordo com seu nível de permissão.
            controlePermissoesBotoes("cliente");
        }
    });
}

function inicializaAtividadeGrid() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarAtividade",
        data: {
            TIPO_REQUISICAO: 2,
            CODATI: 0,
            CODCLI: 0
        },
        success: function(data) {
            data = JSON.parse(data);
            $("#grid-atividades").html("");
            for (var i = 0; i < data.atividades.length; i++) {
                var linhaId    = i+1;
                var linhaClass = ((i+1)%2==1) ? "odd" : null;
                if (i === 0) linhaClass += " selected";
                var linha = "<tr id='"+linhaId+"' class='"+linhaClass+"' desati='" + data.atividades[i].desati+ "' obsandati='" + data.atividades[i].obsandati + "'>" + 
                            "    <td class='codati'>" + data.atividades[i].codati + "</td>" + 
                            "    <td class='nomati'>" + data.atividades[i].nomati + "</td>" + 
                            "    <td class='codtipati' id='" + data.atividades[i].codtipati + "'>" + data.atividades[i].nomtipati + "</td>" + 
                            "    <td class='codcli' id='" + data.atividades[i].codcli+ "'>" + data.atividades[i].nomcli + "</td>" + 
                            "    <td class='status'>" + ((data.atividades[i].status === "S") ? "Concluído" : "Em andamento")  + "</td>" + 
                            "    <td class='datini'>" + data.atividades[i].datini + "</td>" + 
                            "    <td class='datfin'>" + data.atividades[i].datfin + "</td>" + 
                            "</tr>";
                
                linha = linha.replace("undefined", " - ");
                linha = linha.replace("undefined", " - ");
                $("#grid-atividades").append(linha);
            }
            
            // Verifica quais botoes estarao disponiveis para o usuario, de acordo com seu nível de permissão.
            controlePermissoesBotoes("atividade");
        }
    });
}


/**
 * Métodos usados para o controle de permissão de tela.
 **/
function controleDePermissoes() {
	var telas              = ["perfil", "permissao", "empresa", "cliente", "atividade"];
        var telasUsuario       = ["perfil"];
	var telasFuncionario   = ["perfil", "cliente", "atividade"];
	var telasAdministrador = ["perfil", "permissao", "cliente", "atividade"];
	var telasGerente       = ["perfil", "permissao", "empresa", "cliente", "atividade"];
        
	$.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/LoginServlet",
        data: {
            TIPO_REQUISICAO: 4
        },
        success: function(data) {
            permissao = null;
            if (data == "0") {
                permissao = telasUsuario;
            } else if (data == "1") {
                permissao = telasFuncionario;
            } else if (data == "2") {
                permissao = telasAdministrador;
            } else if (data == "3") {
                permissao = telasGerente;
            }
            for (var i = 0; i < telas.length; i++) {
                if (permissao.indexOf(telas[i]) === -1) {
                        $("#"+telas[i]).remove();
                }
            }
        }
    });
}

function controlePermissoesBotoes(tela) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/LoginServlet",
        data: {
            TIPO_REQUISICAO: 4
        },
        success: function(data) {
            var permissoes = retornaTipoFuncionario(data);
            var botoesFunc = retornaBotoesTela(tela, permissoes);
            // Conjunto de botoes usado para comparar: É usado o perfil do gerente por este possuir todos 
            // os privilégios.
            var botoesTela = retornaBotoesTela(tela, PermissoesGerente);

            for (var i = 0; i < botoesTela.length; i++) {
                if (botoesFunc.indexOf(botoesTela[i]) == -1) {
                    $("#"+botoesTela[i]).remove();
                }
            }
        }
    });
}

function retornaTipoFuncionario(tipoFuncionario) {
    if (tipoFuncionario == 0) {
        return PermissoesUsuario;
    } else if (tipoFuncionario == 1) {
        return PermissoesFuncionario;
    } else if (tipoFuncionario == 2) {
        return PermissoesAdministrador;
    } else if (tipoFuncionario == 3) {
        return PermissoesGerente;
    }
    
    return null;
}

function retornaBotoesTela(tela, permissoes) {
    if (tela == "perfil") {
        return permissoes.perfil;
    } else if (tela == "permissao") {
        return permissoes.permissao;
    } else if (tela == "empresa") {
        return permissoes.empresa;
    } else if (tela == "cliente") {
        return permissoes.cliente;
    } else if (tela == "atividade") {
        return permissoes.atividade;
    }
    
    return null;
}


$(document).ready(function() {
    /*
     *Inicializa a tela de Perfil do usuário assim que o sistema é aberto.
     **/
    controleDePermissoes();
    controlador = new ControleTela();
    esquema     = controlador.getEsquemaTela(1);
    setEsquema(esquema);
    InicializaPerfil();
    
    /*
     *Controlador do botao de perfil.
     **/
    $("#perfil").click(function() {	
        controlador = new ControleTela();
        esquema     = controlador.getEsquemaTela(1);
        setEsquema(esquema);
        InicializaPerfil();
    });

    /*
     *Controlador do botao de permissoes.
     **/
    $("#permissao").click(function() {
        controlador = new ControleTela();
        esquema     = controlador.getEsquemaTela(2);
        setEsquema(esquema);
        inicializaGridPermissao();
    });

    /*
     * Controlador do botao de empresas.
     **/
    $("#empresa").click(function() {
        controlador = new ControleTela();
        esquema     = controlador.getEsquemaTela(3);
        setEsquema(esquema);
        inicializarEmpresa();
    });

    /*
     * Controlador do botao de clientes.
     **/
    $("#cliente").click(function() {
        controlador = new ControleTela();
        esquema     = controlador.getEsquemaTela(4);
        setEsquema(esquema);
        inicializaClienteGrid();
    });

    /*
     * Controlador do botao de atividades.
     **/
    $("#atividade").click(function() {
        controlador = new ControleTela();
        esquema     = controlador.getEsquemaTela(5);
        setEsquema(esquema);
        inicializaAtividadeGrid();
    });	
});




/*
 * Objeto contendo os botoes de acesso permitido aos usuários de nível de acesso de usuário.
 **/
PermissoesUsuario = {
    perfil: ["salvar", "excluir"]
};

/*
 * Objeto contendo os botoes de acesso permitido aos usuários de nível de acesso de funcionário.
 **/
PermissoesFuncionario = {
    perfil: ["salvar", "excluir"],
    cliente: ["exibir-historico", "novo", "pesquisar", "editar", "excluir"],
    atividade: ["cadastrar-convite", "novo", "pesquisa", "editar", "excluir"]
};

/*
 * Objeto contendo os botoes de acesso permitido aos usuários de nível de acesso de administrador.
 **/
PermissoesAdministrador = {
    perfil: ["salvar", "excluir"],
    permissao: ["pesquisar", "aprovar-vinculo", "reprovar-vinculo"], 
    cliente: ["exibir-historico", "novo", "pesquisar", "editar", "excluir"],
    atividade: ["cadastrar-convite", "novo", "pesquisa", "editar", "excluir"]
};

/*
 * Objeto contendo os botoes de acesso permitido aos usuários de nível de acesso de Gerente.
 **/
PermissoesGerente = {
    perfil: ["salvar", "excluir"],
    permissao: ["pesquisar", "aprovar-vinculo", "reprovar-vinculo", "atribuir-permissao"], 
    empresa: ["salvar", "excluir"],
    cliente: ["exibir-historico", "novo", "pesquisar", "editar", "excluir"],
    atividade: ["cadastrar-convite", "novo", "pesquisa", "editar", "excluir"]
};

/*
 * Esquema das telas. Os objetos abaixo sao usados para que apenas um arquivo de HTML sirva de base para toda 
 * a aplicacao.
 **/
TelaPerfil = {
    idTela: "tela-perfil",
    scripts: "<script type='text/javascript' src='../js/telas/perfil.js'></script>",
    botoes: "<input id='salvar' class='botao' type='button' value='Salvar'>" + 
	        "<input id='excluir' class='botao' type='button' value='Excluir'>",
    titulo: "PERFIL",
    tela: "<label class='rotuloGrande'>Código:</label>"         + 
          "<input type='text' class='entrada' id='codigo' disabled=''><br>" +
          "<label class='rotuloGrande'>Nome:</label>"           +
          "<input type='text' class='entrada' id='nome'><br>"             +
          "<label class='rotuloGrande'>Login:</label>"          +
          "<input type='text' class='entrada' id='login'><br>"             +
          "<label class='rotuloGrande'>Senha:</label>"          +
          "<input type='password' class='entrada' id='senha'><br>"         +
          "<label class='rotuloGrande'>Confirmar Senha:</label>"          +
          "<input type='password' class='entrada' id='confirmar-senha'><br>"         +
          "<label class='rotuloGrande'>Empresa:</label>"        +
          "<select id='lista-empresa' class='entrada' id='empresas'></select>"
};


TelaPermissoes = {
        idTela: "tela-permissoes",
        scripts: "<script type='text/javascript' src='../js/telas/permissoes.js'></script>",
	botoes: "<input id='pesquisar' class='botao' type='button' value='Pesquisar'> " +
			 "<input id='aprovar-vinculo' class='botao' type='button' value='Aprovar Vínculo'> " +
			 "<input id='reprovar-vinculo' class='botao' type='button' value='Reprovar Vínculo'> " +
			 "<input id='atribuir-permissao' class='botao' type='button' value='Atribuir Permissão'>",
	titulo: "PERMISSÕES",
	tela: "<table class='tabelaResultado'> "+
            "  <thead> "+
            "     <tr class='table_header'> "+
            "	      <th>Nome</th> "+
            "  	      <th>Tipo</th> "+
            "	      <th>Solicitante</th> "+
            "	      <th>Data Inicio</th> "+
            "	      <th>Data Fim</th> "+
            "	  </tr> "+
            "	</thead> "+
            "	<tbody> "+
            "	</tbody> "+
            " </table>"+
            " <div class='popup' id='popup-pesquisa' style='display: none'>" + 
            " 	<div class='cabecalho-popup'>" + 
            " 		<div class='titulo-popup'>Pesquisar</div>" + 
            "		<div class='botao-fechar'><img src='../imagens/close.png'></div>" + 
            "	</div>" + 
            "	<div class='corpo-popup'>" + 
            "		<label for='popup-nome'>Nome</label><br>" + 
            "		<input type='text' id='popup-nome'><br>" + 
            "		<label for='popup-datini'>Data Início</label><br>" + 
            "		<input type='text' id='popup-datini'><br>" + 
            "		<label for='popup-datfim'>Data Fim</label><br>" + 
            "		<input type='text' id='popup-datfim'><br>" + 
            "		<label for='popup-solicitante'>Solicitante</label>" + 
            "		<input type='checkbox' id='popup-solicitante'><br><br>" + 
            "		<input type='button' id='popup-pesquisar' value='pesquisar'>" + 
            "	</div>" + 
            " </div>" + 
            " <div class='popup' id='popup-atribuir-permissao' style='display: none'>" + 
            " 	<div class='cabecalho-popup'>" + 
            " 		<div class='titulo-popup'>Atribuir Permissão</div>" + 
            "		<div class='botao-fechar'><img src='../imagens/close.png'></div>" + 
            "	</div>" + 
            "	<div class='corpo-popup'>" + 
            "	    <input type='radio' name='tipo-funcionario' id='popup-opcao-funcionario' checked>" + 
            "	    <label for='popup-opcao-funcionario'>Funcionário</label><br>" + 
            "	    <input type='radio' name='tipo-funcionario' id='popup-opcao-administrador'>" + 
            "	    <label for='popup-opcao-administrador'>Administrador</label><br><br>" + 
            "	    <input type='button' id='popup-ok' value='Ok'>" + 
            "	</div>" + 
            "</div>"
};


TelaEmpresa = {
        idTela: "tela-empresa",
        scripts: "<script type='text/javascript' src='../js/telas/empresa.js'></script>",
	botoes: "<input id='salvar' class='botao' type='button' value='Salvar'> " +
		"<input id='excluir' class='botao' type='button' value='Excluir'>",
	titulo: "EMPRESA",
	tela: "<label class='rotuloGrandeDireita'>Código da empresa:</label> " + 
            "<input type='text' class='entrada' id='codemp' disabled><br> " + 
            "<label class='rotuloGrande'>Inscrição Jurídica:</label> " + 
            "<input type='text' class='entrada' id='insjur' disabled><br> " + 
            "<label class='rotuloGrande'>Nome Fantasia:</label> " + 
            "<input type='text' class='entrada' id='nomfan'> " + 
            "<label class='rotuloGrandeDireita'>Razão Social:</label> " + 
            "<input type='text' class='entrada' id='razsoc'><br> " + 
            "<label class='rotuloGrande'>Telefone:</label> " + 
            "<input type='text' class='entrada' id='telemp'> " + 
            "<label class='rotuloGrandeDireita'>Celular:</label> " + 
            "<input type='text' class='entrada' id='celemp'>" 
};

popupHistoricoVendedores = " <div class='popup' id='popup-historico-vendedores' style='display: none'>" + 
            " 	<div class='cabecalho-popup'>" + 
            " 		<div class='titulo-popup'>Histórico de Vendedores</div>" + 
            "		<div class='botao-fechar'><img src='../imagens/close.png'></div>" + 
            "	</div>" + 
            "	<div class='corpo-popup'>" + 
            "		<table class='tabelaResultado'> " + 
            "               <thead> " + 
            "                   <tr> " + 
            "                       <th>Vendedor</th> " + 
            "                       <th>Início de vigência</th> " + 
            "                       <th>Fim de vigência</th> " + 
            "                   </tr> " +
            "               </thead> " + 
            "               <tbody id='body-historico-vendedores'> " + 
            "               </tbody> " + 
            "           </table> " + 
            "	</div>" + 
            " </div>";
        
popupPesquisaVendedores = " <div class='popup' id='popup-pesquisa' style='display: none'>" + 
            " 	<div class='cabecalho-popup'>" + 
            " 		<div class='titulo-popup'>Pesquisar</div>" + 
            "		<div class='botao-fechar'><img src='../imagens/close.png'></div>" + 
            "	</div>" + 
            "	<div class='corpo-popup'>" + 
            "		<label for='popup-nomfan'>Nome Fantasia</label><br>" + 
            "		<input type='text' id='popup-nomfan'><br>" + 
            "		<label for='popup-razsoc'>Razão Social</label><br>" + 
            "		<input type='text' id='popup-razsoc'><br>" + 
            "		<label for='popup-inscjur'>Inscrição Jurídica</label><br>" + 
            "		<input type='text' id='popup-inscjur'><br>" + 
            "		<label for='popup-nomven'>Vendedor</label><br>" + 
            "		<input type='text' id='popup-nomven'><br>" + 
            "		<input type='button' id='popup-botao-pesquisar' value='Pesquisar'><br>" + 
            "	</div>" + 
            " </div>";

TelaClienteGrid = {
        idTela: "tela-cliente-grid",
        scripts: "<script type='text/javascript' src='../js/telas/cliente.js'></script>",
	botoes: "<input id='exibir-historico' class='botaoConvite' type='button' value='Histórico de vendedores'>" + 
		"<input id='novo' class='botao' type='button' value='Novo'>" + 
		"<input id='pesquisar' class='botao' type='button' value='Pesquisar'>" + 
		"<input id='editar' class='botao' type='button' value='Editar'>" + 
		"<input id='excluir' class='botao' type='button' value='Excluir'>",
	titulo: "CLIENTE",
	tela: "<table class='tabelaResultado'> " + 
			"<thead> " + 
			"	<tr> " + 
			"		<th>Nome fantasia</th> " + 
			"		<th>Razão social</th> " + 
			"		<th>Inscrição Jurídica</th> " + 
			"		<th>Vendedor atual</th> " + 
                        "		<th>Telefone</th> " + 
                        "		<th>Celular</th> " + 
			"	</tr> " + 
			"</thead> " + 
			"<tbody> " + 
			"</tbody> " + 
		"</table>" + popupHistoricoVendedores + popupPesquisaVendedores
};

TelaClienteForm = {
        idTela: "tela-cliente-form",
        scripts: "<script type='text/javascript' src='../js/telas/cliente.js'></script>",
	botoes: "<input id='novo' class='botao' type='button' value='Novo'>" + 
		"<input id='salvar' class='botao' type='button' value='Salvar'>" + 
		"<input id='editar' class='botao' type='button' value='Grid'>",
	titulo: "CLIENTE",
	tela: "<label for='codcli' class='rotuloGrande'>Código:</label> " + 
              "<input type='text' class='entrada' id='codcli' disabled=''><br> " + 
              "<label  for='nomfan' class='rotuloGrande'>Nome Fantasia:</label> " + 
              "<input type='text' class='entrada' id='nomfan'><br> " + 
              "<label  for='razsoc' class='rotuloGrande'>Razão Social:</label> " + 
              "<input type='text' class='entrada' id='razsoc'><br> " + 
              "<label  for='insjur' class='rotuloGrande'>Inscrição Jurídica:</label> " + 
              "<input type='text' class='entrada' id='insjur'><br> " + 
              "<label  for='telemp' class='rotuloGrande'>Telefone:</label> " + 
              "<input type='text' class='entrada' id='telemp'><br> " + 
              "<label  for='celemp' class='rotuloGrande'>Celular:</label> " + 
              "<input type='text' class='entrada' id='celemp'><br> " + 
              "<label  for='lista-vendedores' class='rotuloGrande'>Vendedor Responsável:</label> " + 
              "<select id='lista-vendedores'></select> " + 
                  popupHistoricoVendedores + 
                  popupPesquisaVendedores
};

popupPesquisaAtividades = " <div class='popup-convite' id='popup-convite' style='display: none'>" + 
            " 	<div class='cabecalho-popup-convite'>" + 
            " 	    <div class='titulo-popup'>Cadastrar Convite</div>" + 
            "	    <div class='botao-fechar'><img src='../imagens/close.png'></div>" + 
            "	</div>" + 
            "	<div class='corpo-popup-convite'>" + 
            "       <div class='campos-convite'> " + 
            "           <label for='descon'>Convite</label><br> " + 
            "           <textarea id='descon' cols='70' rows='4'></textarea><br>" + 
            "           <input type='radio' name='idtiplem' id='tiplem-1' checked> " + 
            "           <label for='tiplem-1'>Enviar lembrete no dia</label><br> " + 
            "           <input type='radio' name='idtiplem' id='tiplem-2'> " + 
            "           <label for='tiplem-2'>Enviar Lembrete todos os dias</label><br><br> " + 
            "           <label for='datinilem'>Data para envio de convite</label> " + 
            "           <input type='text' id='datinilem'><br><br> " +
            "           <label for='convidados'>Funcionários Convidados</label><br> " + 
            "           <select multiple='multiple' id='convidados'> " + 
            "           </select> <br><br>" + 
            "           <button id='enviar-convites'>Enviar Convites</button>"
            "       </div> " + 
            "	</div>" + 
            " </div>";

popupCadastrarConvites = " <div class='popup' id='popup-pesquisa' style='display: none'>" + 
            " 	<div class='cabecalho-popup'>" + 
            " 		<div class='titulo-popup'>Pesquisar</div>" + 
            "		<div class='botao-fechar'><img src='../imagens/close.png'></div>" + 
            "	</div>" + 
            "	<div class='corpo-popup'>" + 
            "       <label for='pesquisa-codati'>Código: </label><br>" + 
            "       <input type='text' id='pesquisa-codati'><br>" + 
            "       <label for='pesquisa-nomati'>Atividade: </label><br>" + 
            "       <input type='text' id='pesquisa-nomati'><br>" + 
            "       <label for='pesquisa-status'>Concluída: </label>" + 
            "       <input type='checkbox' id='pesquisa-status'><br>" + 
            "       <label for='pesquisa-datini'>Data início: </label><br>" + 
            "       <input type='text' id='pesquisa-datini'><br>" + 
            "       <label for='pesquisa-datfin'>Data fim: </label><br>" + 
            "       <input type='text' id='pesquisa-datfin'><br><br>" + 
            "       <button id='button-pesquisar'>Pesquisar</button>" + 
            "	</div>" + 
            " </div>";

TelaAtividadeGrid = {
        idTela: "tela-atividade-grid",
        scripts: "<script type='text/javascript' src='../js/telas/atividade.js'></script>",
	botoes: "<input id='cadastrar-convite' class='botaoConvite' type='button' value='Cadastrar Convites'> " +
                "<input id='novo' class='botao' type='button' value='Novo'> " +
                "<input id='pesquisa' class='botao' type='button' value='Pesquisar'> " +
                "<input id='editar' class='botao' type='button' value='Editar'> " +
                "<input id='excluir' class='botao' type='button' value='Excluir'>",
	titulo: "ATIVIDADE",
	tela: "<table class='tabelaResultado'> " + 
		"	<thead> " + 
		"		<tr> " + 
		"			<th>Cod Atividade</th> " + 
		"			<th>Atividade</th> " + 
		"			<th>Tipo</th> " + 
                "			<th>Cliente</th> " + 
		"			<th>Status</th> " + 
		"			<th>Data Fim</th> " + 
		"			<th>Data Inicio</th> " + 
		"		</tr> " + 
		"	</thead> " + 
                "	<tbody id='grid-atividades'> " + 
                "	</tbody> " + 
		"</table>" + popupPesquisaAtividades + popupCadastrarConvites
};

TelaAtividadeForm = {
        idTela: "tela-atividade-form",
        scripts: "<script type='text/javascript' src='../js/telas/atividade.js'></script>",
	botoes: "<input id='novo' class='botao' type='button' value='Novo'> " +
			"<input id='salvar' class='botao' type='button' value='Salvar'> " +
			"<input id='editar' class='botao' type='button' value='Grid'> ",
	titulo: "ATIVIDADE",
	tela: "<label for='codati' class='rotuloGrande'>Código de atividade:</label> " +
	      "<input type='text' id='codati' class='entrada' disabled><br> " +
              "<label for='nomati' class='rotuloGrande'>Atividade:</label> " +
	      "<input type='text' id='nomati' class='entrada'><br> " +
              "<label for='codtipati' class='rotuloGrande'>Tipo de atividade:</label> " +
	      "<select id='codtipati' name='T' class='entrada'></select><br> " +
              "<label for='codcli' class='rotuloGrande'>Cliente:</label> " +
	      "<select id='codcli' name='T' class='entrada'></select><br> " +
	      "<label for='status' class='rotuloGrande'>Concluido:</label> " +
	      "<input type='checkbox' class='entrada' id='status'><br> " +
	      "<label for='desati' class='rotuloGrande'>Descrição:</label> " +
	      "<textarea id='desati' cols='60' rows='5' ></textarea><br><br> " +
	      "<label for='datini' class='rotuloGrande'>Data Inicio:</label> " +
	      "<input type='text' id='datini' class='entrada'><br> " +
	      "<label for='datfin' class='rotuloGrande'>Data Fim:</label> " +
	      "<input type='text' id='datfin' class='entrada'><br> " +
	      "<label for='obsandati' class='rotuloGrande'>Observações:</label> " +
	      "<textarea id='obsandati' cols='60' rows='5'></textarea><br> " + popupCadastrarConvites
};