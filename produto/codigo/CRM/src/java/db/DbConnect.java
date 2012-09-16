/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PauloHenrique
 */
public class DbConnect {
    private static final String URL  = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String USER = "CRM";
    private static final String PASS = "CRM";
    private static DbConnect instance;
    private static Connection connection;
    
    private DbConnect() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        this.connection = DriverManager.getConnection(
            DbConnect.URL, DbConnect.USER, DbConnect.PASS
        );
    }
    
    public static Connection getConnection() throws SQLException {
        if (DbConnect.instance == null) {
            DbConnect.instance = new DbConnect();
        }
        
        return DbConnect.instance.connection;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
}
