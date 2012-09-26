/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import db.DbStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PauloHenrique
 */
public class Util {
    public static int getNextValidKey(String tabela, String coluna) {
        String query = "SELECT MAX(" + coluna + ") + 1 FROM " + tabela;
        try {
            ResultSet result = DbStatement.select(query);
            result.next();
            return result.getInt(1) == 0? 1 : result.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
}
