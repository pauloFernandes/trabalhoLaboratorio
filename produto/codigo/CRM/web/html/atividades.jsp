﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/Site.css" />
</head>
<body>

    <div class="header">
    </div>
    <div class="menu">
        <input id="Button1" class="botaoMenu" type="button" value="Meu Perfil" /><br />
        <br />
        <input id="Button2" class="botaoMenu" type="button" value="Permissões" /><br />
        <br />
        <input id="Button3" class="botaoMenu" type="button" value="Empresa" /><br />
        <br />
        <input id="Button4" class="botaoMenu" type="button" value="Cliente" /><br />
        <br />
        <input id="Button5" class="botaoMenu" type="button" value="Atividade" />
    </div>
    <img alt="" class="imgUsuario" src="" />
    <img alt="" class="imgCRM" src="" />
    <input id="Button11" class="botaoConvite" type="button" value="Cadastrar Convites" />
    <input id="Button6" class="botao" type="button" value="Novo" />
    <input id="Button7" class="botao" type="button" value="Pesquisar" />
    <input id="Button8" class="botao" type="button" value="Salvar" />
    <input id="Button9" class="botao" type="button" value="Editar" />
    <input id="Button10" class="botao" type="button" value="Excluir" />
        
    <div class="tela">
    <form id="atividade" name="atividade">
    <label for="tipo" class="rotuloGrande">Tipo de atividade:</label>
    <select id="tipo" name="T" class="entrada">
        <option></option>
    </select>
    <label for="status" class="rotuloDireita">Status:</label>
    <input type="text" class="entrada" id="status" />
    <br />
    <label for="codA" class="rotuloGrande">Código de atividade:</label>
    <input type="text" class="entrada" />
    <label for="codF" class="rotuloGrandeDireita">Código de funcionario:</label>
    <input type="text" class="entrada" /><br />
    <label for="ativ" class="rotuloGrande">Atividade:</label>
    <input type="text" class="entrada" /><br />
    <label for="desc" class="rotuloGrande">Descrição:</label>
    <textarea id="desc" cols="20" rows="2" class="entrada"></textarea><br />
    <label for="datIni" class="rotuloGrande">Data Inicio:</label>
    <input type="text" id="datIni" class="entrada" />
    <label for="datFim" class="rotuloGrandeDireita">Data Fim:</label>
    <input type="text" id="datFim" class="entrada" /><br />
    <label for="obs" class="rotuloGrande">Observações:</label>
    <textarea id="obs" cols="20" rows="2" class="entrada"></textarea><br /> 
    
    </form>
    </div>

    <div class="titulo">
        ATIVIDADES&nbsp;</div>

</body>
</html>