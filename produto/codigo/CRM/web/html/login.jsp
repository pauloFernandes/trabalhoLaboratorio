<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="../js/jquery/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="../js/jquery/jquery-ui-1.8.23.custom.min.js"></script>
    <script type="text/javascript" src="../js/login.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/Site.css" />
</head>
<body>

    <div class="header">
        <img alt="" class="imgCRM" src="" />
        <form style="position: relative; font-family: Arial, Helvetica, sans-serif; color: #000000; top: 28px; left: 553px; width: 552px;">
            <label for="log">Login:</label>
            <input type="text" id="log" />
            <label for="pass">Senha:</label>
            <input type="password" id="pass"/>
            <input type="button" value="Entrar" id="entrar" />
        </form>
    </div>

    <div style="position: relative; font-family: Arial, Helvetica, sans-serif; color: #FFFFFF; top: 1px; left: 556px; height: 433px; width: 550px;">
        Ainda não é cadastrado? Então:
        <br />
        <br />
        <br />
        <h4>Cadastre-se:</h4>
        <br />
        <form>
        <label class="rotuloGrande">Nome:</label>
        <input type="text" class="entrada" id="nome" /><br />
        <label class="rotuloGrande">Login:</label>
        <input type="text" class="entrada" id="login" /><br />
        <label class="rotuloGrande">Senha:</label>
        <input type="password" class="entrada" id="senha" /><br />
        <input type="radio" name="radio" id="radio-nova-empresa" value="nova" checked/>
        <label for="nova">Cadastrar nova empresa:</label>
        <input type="text" /><br />
        <input type="radio" name="radio" id="radio-associar-empresa" value="ass" />
        <label for="ass">Associar-se a empresa existente:</label>
        <select></select><br />
        <input type="submit" value="Cadastrar" id="cadatrar" />
        </form>
    </div>

</body>
</html>
