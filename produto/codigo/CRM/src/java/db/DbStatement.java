/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import dao.ArquivoExterno;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PauloHenrique
 */
public class DbStatement {
    private Statement statement;
    
    public static ResultSet select(String query) throws SQLException {
        try {
            Statement statement = DbConnect.getConnection().createStatement();
            //---------
            try {
                ArquivoExterno.salvar("c:\\Users\\PauloHenrique\\Desktop\\teste.txt", query, true);
            } catch (IOException ex) {
                Logger.getLogger(DbStatement.class.getName()).log(Level.SEVERE, null, ex);
            }
            //---------
            return statement.executeQuery(query);
        } catch (SQLException e) {
            //---------
            try {
                ArquivoExterno.salvar("c:\\Users\\PauloHenrique\\Desktop\\teste.txt", e.getMessage(), true);
            } catch (IOException ex) {
                Logger.getLogger(DbStatement.class.getName()).log(Level.SEVERE, null, ex);
            }
            //---------
        }
        return null;
    }
    
    public static void insert(String query) throws SQLException {
        Statement statement = DbConnect.getConnection().createStatement();
        statement.execute(query);
    }
    
    public static void update(String query) throws SQLException {
        Statement statement = DbConnect.getConnection().createStatement();
        statement.execute(query);
    }
    
    public static void delete(String query) throws SQLException {
        Statement statement = DbConnect.getConnection().createStatement();
        statement.execute(query);
    }
    
}
