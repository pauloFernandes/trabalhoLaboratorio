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
public class Empresa {
    public static ResultSet obterTodosClientes(int codemp) {
        String sql = " SELECT CL.NOMFAN, CL.RAZSOC, CL.NUMINSJUR, FR.CODFUN, U.NOMUSU " +
                        " FROM CLIENTE CL, FUN_RESPONSAVEL FR, FUNCIONARIO F, USUARIO U " +
                        " WHERE U.CODUSU  = F.CODUSU  " +
                          " AND F.CODFUN  = FR.CODFUN " +
                          " AND FR.CODCLI = CL.CODCLI " +
                          " AND CL.CODEMP =  " + codemp +
                          " AND FR.DATINI IS NOT NULL " +
                          " AND FR.DATFIM IS NULL;";
        try {
            return DbStatement.select(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
