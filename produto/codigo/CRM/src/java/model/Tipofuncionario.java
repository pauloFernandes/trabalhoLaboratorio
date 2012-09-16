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
public class Tipofuncionario {
    public static String getAllTipoFuncionarios() throws SQLException {
        String sql = "SELECT nomtipfun FROM TIPOFUNCIONARIO";
        ResultSet resultSet = DbStatement.select(sql);
        String returnString = "";
        
        while (resultSet.next()) {
            returnString += resultSet.getString(1) + "  " ;
        }
        
        return returnString;
    }
}
