/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanager.repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author EMA
 */
public class DBConnectionFactory {
    private Connection connection;
    private static DBConnectionFactory instance;
    
    private DBConnectionFactory() {
    }
    
    public static DBConnectionFactory getInstance() {
        if (instance == null)
            instance = new DBConnectionFactory();
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/projectmanager";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        }
        return connection;
    }
}
