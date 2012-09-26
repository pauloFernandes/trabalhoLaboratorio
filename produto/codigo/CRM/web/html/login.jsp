<%-- 
    Document   : login
    Created on : 20/09/2012, 14:13:54
    Author     : PauloHenrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="../js/jquery/jquery-1.8.0.min.js"></script>
        <script type="text/javascript" src="../js/jquery/jquery-ui-1.8.23.custom.min.js"></script>
        <script type="text/javascript" src="../js/login.js"></script>
    </head>
    <body>
        Nome:
        <input type="text" id="nome" ><br>
        Login: 
        <input type="text" id="login" ><br>
        Senha:
        <input type="password" id="senha"><br>
        
        <input type="radio" name="tipo-empresa" id="radio-nova-empresa" checked="checked">Cadastrar Nova Empresa<br>
        <input type="radio" name="tipo-empresa" id="radio-associar-empresa">Associar-se a Empresa<br><br>
        
        <div id="nova-empresa" style="display: none">
            <select id="empresa-selecionada">
                <option value="1">Empresa 1</option>
                <option value="2">Empresa 2</option>
                <option value="3">Empresa 3</option>
            </select>
        </div>
        <div id="associar-empresa" style="display: none">
            Inscricao juridica
            <input type="text" id="inscjur"><br>
            nome fantasia
            <input type="text" id="nomfan"><br>
            razao social
            <input type="text" id="razsoc">
        </div>
        
        <input type="button" id="cadatrar" value="cadastrar">
    </body>
</html>
