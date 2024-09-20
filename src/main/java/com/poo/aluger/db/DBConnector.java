package com.poo.aluger.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        try(FileInputStream fis = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(fis);

            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password")
            );
        }
    }
}
