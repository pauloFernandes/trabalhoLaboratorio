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
        <label for="nome">Nome</label>
        <input type="text" id="nome"><br>
        <label for="login">Login</label>
        <input type="text" id="login"><br>
        <label for="senha">Senha</label>
        <input type="password" id="senha"><br>
        <input type="button" id="button-cadastrar-empresa" value="Cadastrar Nova Empresa">
        <input type="button" id="button-associar-empresa" value="Associar-se à Empresa">
        <div id="escolher-empresa" style="display: none">
            <label for="lista-empresa">Empresa</label>
            <select id="lista-empresa">
                <option value="">Nenhum selecionado</option>
                <option value="emp1">Empresa 1</option>
                <option value="emp2">Empresa 2</option>
                <option value="emp3">Empresa 3</option>
                <option value="emp4">Empresa 4</option>
            </select>
        </div>
        <div id="cadastrar-empresa" style="display: none">
            <label for="nova-empresa">Empresa</label>
            <input type="text" id="nova-empresa">
        </div><br>
        <input type="button" id="cadastrar" value="Cadastre-se">
    </body>
</html>
