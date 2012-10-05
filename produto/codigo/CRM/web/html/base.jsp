<%-- 
    Document   : base
    Created on : 29/09/2012, 02:48:08
    Author     : PauloHenrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>CRM</title>
    <script type="text/javascript" src="../js/jquery/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="../js/jquery/jquery-ui-1.8.23.custom.min.js"></script>
    <script type="text/javascript" src="../js/telas/controleTela.js"></script>
    <script type="text/javascript" src="../js/telas/grid.js"></script>
    <script type="text/javascript" src="../js/telas/popup.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/Site.css" />
</head>
<body>

    <div class="header">
    </div>
    <div class="menu">
        <input id="perfil" class="botaoMenu" type="button" value="Meu Perfil" /><br><br>
        <input id="permissao" class="botaoMenu" type="button" value="Permissões" /><br><br>
        <input id="empresa" class="botaoMenu" type="button" value="Empresa" /><br><br>
        <input id="cliente" class="botaoMenu" type="button" value="Cliente" /><br><br>
        <input id="atividade" class="botaoMenu" type="button" value="Atividade" />
    </div>
    <img alt="" class="imgUsuario" src="" />
    <img alt="" class="imgCRM" src="" />
    
    <div class="tela" id="tela"></div>
    <div class="botoes" id="botoes"></div>    
    <div class="titulo" id="titulo"></div>
    <div id="shadow" style="display: none"></div>
    <div id="scripts-temporarios" style="display: none;"></div>
</body>
</html>
