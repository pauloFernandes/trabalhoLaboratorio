/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $("#acessar").click(function() {
        login = $("#login").val();
        senha = $("#senha").val();
        
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/CRM/ServletTeste",
            data: {
                LOGIN: login,
                SENHA: senha
            },
            success: function(data) {
                alert(data);
            }
        });
    });
    
});