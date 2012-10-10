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
        String sql = " SELECT CL.CODCLI, CL.NOMFAN, CL.RAZSOC, CL.NUMINSJUR, CL.CELEMP, CL.TELEMP, FR.CODFUN, U.NOMUSU " + 
                     "   FROM CLIENTE CL " + 
                     "   LEFT JOIN FUN_RESPONSAVEL FR " + 
                     "     ON FR.CODCLI  = CL.CODCLI " + 
                     "     AND FR.DATINI IS NOT NULL " + 
                     "     AND FR.DATFIM IS NULL " + 
                     "   LEFT JOIN FUNCIONARIO F " + 
                     "     ON F.CODFUN   = FR.CODFUN " + 
                     "   LEFT JOIN USUARIO U " + 
                     "     ON U.CODUSU = F.CODUSU " + 
                     "   WHERE CL.CODEMP  = " + codemp;
        try {
            return DbStatement.select(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static ResultSet obterClientes(int codemp, String nomfan, String razsoc, String insjur, String nomven) {
        String sql = " SELECT CL.CODCLI, CL.NOMFAN, CL.RAZSOC, CL.NUMINSJUR, CL.CELEMP, CL.TELEMP, FR.CODFUN, U.NOMUSU " +
                        " FROM CLIENTE CL, FUN_RESPONSAVEL FR, FUNCIONARIO F, USUARIO U " +
                        " WHERE U.CODUSU  = F.CODUSU  " +
                          " AND F.CODFUN  = FR.CODFUN " +
                          " AND FR.CODCLI = CL.CODCLI " +
                          " AND CL.CODEMP =  " + codemp +
                          " AND FR.DATINI IS NOT NULL " +
                          " AND FR.DATFIM IS NULL";
        if (!nomfan.isEmpty()) {
            sql += " AND UPPER(CL.NOMFAN) LIKE UPPER('" + nomfan + "%')";
        }
        
        if (!razsoc.isEmpty()) {
            sql += " AND UPPER(CL.RAZSOC) LIKE  UPPER('" + razsoc + "%') ";
        }
        
        if (!insjur.isEmpty()) {
            sql += " AND UPPER(CL.NUMINSJUR) LIKE  UPPER('" + insjur + "%') ";
        }
        
        if (!nomven.isEmpty()) {
            sql += " AND UPPER(U.NOMUSU) LIKE UPPER('" + nomven + "%') ";
        }
        
        try {
            return DbStatement.select(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
