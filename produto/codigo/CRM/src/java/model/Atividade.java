/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.DbStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PauloHenrique
 */
public class Atividade {
    public static ResultSet obterAtividades(int codemp, int codfunres, int codati, int codcli, String nomati, String status, 
                                            String datini, String datfim) throws SQLException {
        String sql = " SELECT A.CODATI, A.NOMATI, A.DESATI, A.CODTIPATI, TA.NOMTIPATI, A.STATUS, A.CODFUNRES, U.NOMUSU, A.DATINI, A.DATFIN, A.OBSANDATI, A.CODCLI, C.RAZSOC " + 
                     "   FROM ATIVIDADE A, TIPO_ATIVIDADE TA, FUNCIONARIO F, USUARIO U, CLIENTE C " + 
                     "   WHERE TA.CODTIPATI = A.CODTIPATI " + 
                     "     AND A.CODFUNRES  = F.CODFUN " + 
                     "     AND F.CODUSU     = U.CODUSU " + 
                     "     AND A.CODCLI     = C.CODCLI " + 
                     "     AND F.DATINI IS NOT NULL " + 
                     "     AND F.DATFIM IS NULL " + 
                     "     AND A.CODFUNRES  =  " + codfunres +
                     "     AND A.CODEMP     = " + codemp;
        
        if (codati != 0) {
            sql += " AND CODATI = " + codati;
        }
        
        if (codcli != 0) {
            sql += " AND CODCLI = " + codcli;
        }
        
        if (status != null && !status.isEmpty()) {
            sql += " AND STATUS = '" + status + "' ";
        }
        
        if (datini != null && !datini.isEmpty()) {
            sql += " AND TO_CHAR(DATINI, 'DD/MM/YYYY') >= TO_CHAR(" + datini + ", 'DD/MM/YYYY')";
        }
        
        if (datfim != null && !datfim.isEmpty()) {
            sql += " AND TO_CHAR(DATFIN, 'DD/MM/YYYY') =< TO_CHAR(" + datfim + ", 'DD/MM/YYYY')";
        }
        
        if (nomati != null && !nomati.isEmpty()) {
            sql += " AND NOMATI LIKE '%"+nomati+"%' ";
        }
        
        
        return DbStatement.select(sql);
    }
    
}
