/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Ambiente;
import db.DbStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PauloHenrique
 */
public class Funcionario {
    public static ResultSet obterTodosItensPermissao() {
        String sql = " SELECT U.CODUSU, " + 
                    "   U.NOMUSU, " + 
                    "   TF.NOMTIPFUN, " + 
                    "   CASE WHEN F.DATINI IS NULL " + 
                    "     THEN 'SIM' " + 
                    "     ELSE 'NÃO' " + 
                    "   END SOLICITANTE, " + 
                    "   F.DATINI, " + 
                    "   F.DATFIM " + 
                    " FROM USUARIO U, FUNCIONARIO F, TIPOFUNCIONARIO TF " + 
                    " WHERE U.CODUSU     = F.CODUSU " + 
                    "   AND TF.CODTIPFUN = F.CODTIPFUN " + 
                    "   AND F.CODEMP     = " + Ambiente.getInstance().getEmpresaEntity().getCodemp() + 
                    " ORDER BY 3 DESC";
        
        try {
            return DbStatement.select(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static ResultSet obterTodosItensPermissao(String nome, String datini, String datfim, String solicitante) {
        String sql = " SELECT U.CODUSU, " +
                    "   U.NOMUSU, " + 
                    "   TF.NOMTIPFUN, " + 
                    "   CASE WHEN F.DATINI IS NULL " + 
                    "     THEN 'SIM' " + 
                    "     ELSE 'NÃO' " + 
                    "   END SOLICITANTE, " + 
                    "   F.DATINI, " + 
                    "   F.DATFIM " + 
                    " FROM USUARIO U, FUNCIONARIO F, TIPOFUNCIONARIO TF " + 
                    " WHERE U.CODUSU     = F.CODUSU " + 
                    "   AND TF.CODTIPFUN = F.CODTIPFUN " + 
                    "   AND F.CODEMP     = " + Ambiente.getInstance().getEmpresaEntity().getCodemp();
        
        if (!nome.isEmpty()) {
            sql += " AND UPPER(U.NOMUSU) LIKE UPPER('%" + nome + "%') ";
        }
        
        if (!datini.isEmpty()) {
            sql += " AND F.DATINI = '" + datini + "' ";
        }
        
        if (!datfim.isEmpty()) {
            sql += " AND F.DATFIM = '" + datfim + "' ";
        }
        
        if (solicitante != null) {
            sql += " AND F.DATINI IS NULL ";
        }
        
        sql += " ORDER BY 3 DESC";
        
        try {
            return DbStatement.select(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;        
    }
    
    public static ResultSet obterTodosVendedores(int codemp) {
        String sql = "SELECT F.CODFUN, U.NOMUSU " + 
                        " FROM FUNCIONARIO F, USUARIO U " + 
                        " WHERE F.CODUSU = U.CODUSU " + 
                          " AND F.CODEMP =  " + codemp +  
                          " AND F.DATINI IS NOT NULL " + 
                          " AND F.DATFIM IS NULL";
        try {
            return DbStatement.select(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
