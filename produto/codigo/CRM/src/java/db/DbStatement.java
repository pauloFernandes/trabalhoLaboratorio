/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PauloHenrique
 */
public class DbStatement {
    private Statement statement;
    
    public static ResultSet select(String query) throws SQLException {
        Statement statement = DbConnect.getConnection().createStatement();
        return statement.executeQuery(query);
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
