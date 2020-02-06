package com.campsite.api.dao;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class CampsiteDBDao {

    private static final String jdbcDriver = "org.h2.Driver";
    private static final String dbURL = "jdbc:h2:mem:campsitedb";
    private static final String dbUser = "sa";
    private static final String password = "";

    private Connection connection;

    public CampsiteDBDao() {
        try {
            createConnection();
        } catch (Exception e) {
            connection = null;
        }
    }

    private void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(jdbcDriver);
        connection = DriverManager.getConnection(dbURL, dbUser, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            return null;
        }
    }
}
