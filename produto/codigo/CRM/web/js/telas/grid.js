/* 
 * Controles do grid
 */
$(document).ready(function() {
    $("tr:not(tr.table_header)").live("click", function() {
        $("tr.selected").toggleClass("selected");
        $(this).toggleClass("selected");
    });
});