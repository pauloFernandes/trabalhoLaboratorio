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
public class Cliente {
    public static ResultSet obterClientes(int codemp) throws SQLException {
        String sql = "SELECT CODCLI, RAZSOC "
                   + "  FROM CLIENTE "
                   + "  WHERE CODEMP = " + codemp;
        
        return DbStatement.select(sql);
    }
}
