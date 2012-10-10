/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.DbStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PauloHenrique
 */
public class Fun_Responsavel {
    public static ResultSet obterHistoricoVendedores(int codcli, int codemp) {
       String sql = " SELECT  U.NOMUSU, FR.DATINI, FR.DATFIM " + 
                    " FROM FUN_RESPONSAVEL FR, FUNCIONARIO F, USUARIO U " + 
                    " WHERE F.CODFUN = FR.CODFUN " + 
                    "   AND F.CODUSU = U.CODUSU " + 
                    "   AND FR.CODCLI = " + codcli + 
                    "   AND FR.CODEMP = " + codemp;
        try {
            return DbStatement.select(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Fun_Responsavel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return null;
    }
}
