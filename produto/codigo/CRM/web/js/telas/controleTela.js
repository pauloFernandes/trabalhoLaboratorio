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

TelaPerfil = {
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
          "<label class='rotuloGrande'>Empresa:</label>"        +
          "<select id='lista-empresa' class='entrada' id='empresas'></select>"
};

/*
 * @todo: no campo de tela devem ser feitos os popups de pesquisa e atribuicao de permissão.
 */
TelaPermissoes = {
        scripts: "<script type='text/javascript' src='../js/telas/permissoes.js'></script>",
	botoes: "<input id='pesquisar' class='botao' type='button' value='Pesquisar'> " +
			 "<input id='aprovar-vinculo' class='botao' type='button' value='Aprovar Vínculo'> " +
			 "<input id='reprovar-vinculo' class='botao' type='button' value='Reprovar Vínculo'> " +
			 "<input id='atribuir-permissao' class='botao' type='button' value='Atribuir Permissão'>",
	titulo: "PERMISSÕES",
	tela: "<table class='tabelaResultado'> "+
		  "	<thead> "+
		  "		<tr> "+
		  "			<th>Nome</th> "+
		  "			<th>Tipo</th> "+
		  "			<th>Solicitante</th> "+
		  "			<th>Data Inicio</th> "+
		  "			<th>Data Fim</th> "+
		  "		</tr> "+
		  "	</thead> "+
		  "	<tbody> "+
		  "	</tbody> "+
		  "</table>"
};

TelaEmpresa = {
        scripts: "<script type='text/javascript' src='../js/telas/empresa.js'></script>",
	botoes: "<input id='salvar' class='botao' type='button' value='Salvar'> " +
		"<input id='excluir' class='botao' type='button' value='Excluir'>",
	titulo: "EMPRESA",
	tela: "<label class='rotuloGrande'>Código do cliente:</label> " + 
			"<input type='text' class='entrada'> " + 
			"<label class='rotuloGrandeDireita'>Código da empresa:</label> " + 
			"<input type='text' class='entrada'><br> " + 
			"<label class='rotuloGrande'>Inscrição Jurídica:</label> " + 
			"<input type='text' class='entrada'><br> " + 
			"<label class='rotuloGrande'>Nome Fantasia:</label> " + 
			"<input type='text' class='entrada'> " + 
			"<label class='rotuloGrandeDireita'>Razão Social:</label> " + 
			"<input type='text' class='entrada'><br> " + 
			"<label class='rotuloGrande'>Telefone:</label> " + 
			"<input type='text' class='entrada'> " + 
			"<label class='rotuloGrandeDireita'>Celular:</label> " + 
			"<input type='text' class='entrada'>" 
};

TelaClienteGrid = {
        scripts: "<script type='text/javascript' src='../js/telas/cliente.js'></script>",
	botoes: "<input id='exibir-historico' class='botaoConvite' type='button' value='Histórico de vendedores'>" + 
		"<input id='novo' class='botaoConvite' type='button' value='Novo'>" + 
		"<input id='pesquisar' class='botaoConvite' type='button' value='Pesquisar'>" + 
		"<input id='salvar' class='botaoConvite' type='button' value='Salvar'>" + 
		"<input id='editar' class='botaoConvite' type='button' value='Editar'>" + 
		"<input id='excluir' class='botaoConvite' type='button' value='Excluir'>",
	titulo: "CLIENTE",
	tela: "<table class='tabelaResultado'> " + 
			"<thead> " + 
			"	<tr> " + 
			"		<th>Nome fantasia</th> " + 
			"		<th>Razão social</th> " + 
			"		<th>Inscrição Jurídica</th> " + 
			"		<th>Vendedor atual</th> " + 
			"	</tr> " + 
			"</thead> " + 
			"<tbody> " + 
			"</tbody> " + 
		"</table>"
};

TelaClienteForm = {
        scripts: "<script type='text/javascript' src='../js/telas/cliente.js'></script>",
	botoes: "<input id='exibir-historico' class='botaoConvite' type='button' value='Histórico de vendedores'>" + 
		"<input id='novo' class='botaoConvite' type='button' value='Novo'>" + 
		"<input id='pesquisar' class='botaoConvite' type='button' value='Pesquisar'>" + 
		"<input id='salvar' class='botaoConvite' type='button' value='Salvar'>" + 
		"<input id='editar' class='botaoConvite' type='button' value='Editar'>" + 
		"<input id='excluir' class='botaoConvite' type='button' value='Excluir'>",
	titulo: "CLIENTE",
	tela: "<label class='rotuloGrande'>Código:</label> " + 
		  "<input type='text' class='entrada' disabled=''><br> " + 
		  "<label class='rotuloGrande'>Nome:</label> " + 
		  "<input type='text' class='entrada'><br> " + 
		  "<label class='rotuloGrande'>Login:</label> " + 
		  "<input type='text' class='entrada'><br> " + 
		  "<label class='rotuloGrande'>Senha:</label> " + 
		  "<input type='password' class='entrada'><br> " + 
		  "<label class='rotuloGrande'>Empresa:</label> " + 
		  "<select id='lista-empresa' class='entrada'></select>"
};

TelaAtividadeGrid = {
        scripts: "<script type='text/javascript' src='../js/telas/atividade.js'></script>",
	botoes: "<input id='cadastrar-convite' class='botaoConvite' type='button' value='Cadastrar Convites'> " +
                "<input id='novo' class='botao' type='button' value='Novo'> " +
                "<input id='pesquisa' class='botao' type='button' value='Pesquisar'> " +
                "<input id='salvar' class='botao' type='button' value='Salvar'> " +
                "<input id='editar' class='botao' type='button' value='Editar'> " +
                "<input id='excluir' class='botao' type='button' value='Excluir'>",
	titulo: "ATIVIDADE",
	tela: "<table class='tabelaResultado'> " + 
		"	<thead> " + 
		"		<tr> " + 
		"			<th>Tipo</th> " + 
		"			<th>Status</th> " + 
		"			<th>Cod Atividade</th> " + 
		"			<th>Nome Func</th> " + 
		"			<th>Atividade</th> " + 
		"			<th>Data Fim</th> " + 
		"			<th>Data Inicio</th> " + 
		"		</tr> " + 
		"	</thead> " + 
		"</table>"
};

TelaAtividadeForm = {
        scripts: "<script type='text/javascript' src='../js/telas/atividade.js'></script>",
	botoes: "<input id='cadastrar-convite' class='botaoConvite' type='button' value='Cadastrar Convites'> " +
			"<input id='novo' class='botao' type='button' value='Novo'> " +
			"<input id='pesquisa' class='botao' type='button' value='Pesquisar'> " +
			"<input id='salvar' class='botao' type='button' value='Salvar'> " +
			"<input id='editar' class='botao' type='button' value='Editar'> " +
			"<input id='excluir' class='botao' type='button' value='Excluir'>",
	titulo: "CLIENTE",
	tela: "<label for='tipo' class='rotuloGrande'>Tipo de atividade:</label> " +
			"<select id='tipo' name='T' class='entrada'></select> " +
			"<label for='status' class='rotuloDireita'>Status:</label> " +
			"<input type='text' class='entrada' id='status'><br> " +
			"<label for='codA' class='rotuloGrande'>Código de atividade:</label> " +
			"<input type='text' class='entrada'> " +
			"<label for='codF' class='rotuloGrandeDireita'>Código de funcionario:</label> " +
			"<input type='text' class='entrada'><br> " +
			"<label for='ativ' class='rotuloGrande'>Atividade:</label> " +
			"<input type='text' class='entrada'><br> " +
			"<label for='desc' class='rotuloGrande'>Descrição:</label> " +
			"<textarea id='desc' cols='20' rows='2' class='entrada'></textarea><br> " +
			"<label for='datIni' class='rotuloGrande'>Data Inicio:</label> " +
			"<input type='text' id='datIni' class='entrada'> " +
			"<label for='datFim' class='rotuloGrandeDireita'>Data Fim:</label> " +
			"<input type='text' id='datFim' class='entrada'><br> " +
			"<label for='obs' class='rotuloGrande'>Observações:</label> " +
			"<textarea id='obs' cols='20' rows='2' class='entrada'></textarea><br> "
};

$(document).ready(function() {
	$("#perfil").click(function() {	
		controlador = new ControleTela();
		esquema     = controlador.getEsquemaTela(1);
		setEsquema(esquema);
                InicializaPerfil();
	});
	
	$("#permissao").click(function() {
		controlador = new ControleTela();
		esquema     = controlador.getEsquemaTela(2);
		setEsquema(esquema);
	});
	
	$("#empresa").click(function() {
		controlador = new ControleTela();
		esquema     = controlador.getEsquemaTela(3);
		setEsquema(esquema);
	});
	
	$("#cliente").click(function() {
		controlador = new ControleTela();
		esquema     = controlador.getEsquemaTela(4);
		setEsquema(esquema);
	});
	
	$("#atividade").click(function() {
		controlador = new ControleTela();
		esquema     = controlador.getEsquemaTela(5);
		setEsquema(esquema);
	});
	
});

function setEsquema(esquema) {
	$("#titulo").html(esquema.titulo);
	$("#botoes").html(esquema.botoes);
	$("#tela").html(esquema.tela);
        $("#scripts-temporarios").html(esquema.scripts);
}

function InicializaPerfil() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/PerfilServlet",
        data: {
            TIPO_REQUISICAO: 1
        },
        success: function(data) {
            data = JSON.parse(data);
            
            $("#codigo").val(data.codusu);
            $("#nome").val(data.nomusu);
            $("#login").val(data.logusu);
            
            var empresas = "";
            for (var i = 0; i < data.empresas.length; i++) {
                var aux = data.empresas;
                empresas += "<option id='" + aux[i].codemp + "' value='" + aux[i].codemp +"'>" + aux[i].razsoc + "</option>";
            }
            
            $("#lista-empresa").html(empresas);
        }
    });
}

