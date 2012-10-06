/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$("#salvar").click(function() {
    var codemp = $("#codemp").val();
    var insjur = $("#insjur").val();
    var nomfan = $("#nomfan").val();
    var razsoc = $("#razsoc").val();
    var telemp = $("#telemp").val();
    var celemp = $("#celemp").val();
    
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/CRM/GerenciarEmpresa",
        data: {
            TIPO_REQUISICAO: 2,
            CODEMP: codemp,
            NOMFAN: nomfan, 
            RAZSOC: razsoc, 
            TELEMP: telemp,
            CELEMP: celemp
        },
        success: function(data) {
            alert("Registro salvo com sucesso.");
        }
    });
});


/*
 *@todo: este metodo será usado juntamente com uma trigger ou procedure que excluirá todos os registros da empresa
 * removida.
 **/
$("#excluir").click(function() {
    alert("excluir");
});