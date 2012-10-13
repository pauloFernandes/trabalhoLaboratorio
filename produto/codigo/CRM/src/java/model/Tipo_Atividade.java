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
public class Tipo_Atividade {
    public static ResultSet obterTipoAtividade() throws SQLException {
        String sql = " SELECT CODTIPATI, NOMTIPATI " + 
                     "   FROM TIPO_ATIVIDADE ";
        
        
        return DbStatement.select(sql);
    }
}
