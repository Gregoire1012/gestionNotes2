package com.gestionnotes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/gestionnotes?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // ton utilisateur MySQL
    private static final String PASSWORD = "1234"; // ton mot de passe MySQL

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Charger le driver
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}