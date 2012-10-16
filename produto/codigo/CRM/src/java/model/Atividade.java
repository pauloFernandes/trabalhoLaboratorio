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
        String sql = " SELECT * FROM (" + 
                        " SELECT A.CODATI, A.NOMATI, A.DESATI, A.CODTIPATI, TA.NOMTIPATI, A.STATUS, F.CODEMP, " + 
                        "   A.CODFUNRES, F.CODFUN, U.NOMUSU, A.DATINI, A.DATFIN, A.OBSANDATI, A.CODCLI, C.RAZSOC, " + 
                        "   CASE " + 
                        "     WHEN CONV.IDCONACE = 'N' " + 
                        "     THEN 'Não' " + 
                        "     ELSE 'Sim' " + 
                        "   END CONVITE_ACEITO " + 
                        " FROM ATIVIDADE A " + 
                        " INNER JOIN TIPO_ATIVIDADE TA " + 
                        "   ON TA.CODTIPATI = A.CODTIPATI " + 
                        " INNER JOIN FUNCIONARIO F " + 
                        "   ON A.CODFUNRES = F.CODFUN " + 
                        "   AND F.DATINI  IS NOT NULL " + 
                        "   AND F.DATFIM  IS NULL " + 
                        " INNER JOIN USUARIO U " + 
                        "   ON F.CODUSU = U.CODUSU " + 
                        " INNER JOIN CLIENTE C " + 
                        "   ON A.CODCLI = C.CODCLI " + 
                        " LEFT JOIN CONVIDADO CONV " + 
                        "   ON CONV.CODFUN  = A.CODFUNRES " + 
                        "   AND CONV.CODATI = A.CODATI " + 
                        "  " + 
                        " UNION " + 
                        "  " + 
                        " SELECT A.CODATI, A.NOMATI, A.DESATI, A.CODTIPATI, TA.NOMTIPATI, A.STATUS, FUN.CODEMP, " + 
                        "   A.CODFUNRES, FUN.CODFUN, U.NOMUSU, A.DATINI, A.DATFIN, A.OBSANDATI, A.CODCLI, C.RAZSOC,  " + 
                        "   CASE " + 
                        "     WHEN CON.IDCONACE = 'N' " + 
                        "     THEN 'Não' " + 
                        "     ELSE 'Sim' " + 
                        "   END CONVITE_ACEITO " + 
                        "   FROM ATIVIDADE A " + 
                        "   INNER JOIN CONVIDADO CON " + 
                        "     ON CON.CODATI = A.CODATI " + 
                        "   INNER JOIN FUNCIONARIO FUN " + 
                        "     ON fun.codfun = con.codfun " + 
                        "     AND FUN.DATINI IS NOT NULL " + 
                        "     AND FUN.DATFIM IS NULL " + 
                        "   INNER JOIN USUARIO U " + 
                        "     ON FUN.CODFUN = U.CODUSU " + 
                        "   INNER JOIN CLIENTE C " + 
                        "     ON C.CODCLI = A.CODCLI " + 
                        "   INNER JOIN TIPO_ATIVIDADE TA " + 
                        "     ON TA.CODTIPATI = A.CODTIPATI " + 
                    ") " + 
                    " WHERE CODFUN = " + codfunres + 
                    "  AND CODEMP = " + codemp;
        
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
