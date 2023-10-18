package com.smk.cashier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcConnection {
    private static Optional<Connection> conn = Optional.empty();

    public static Optional<Connection> getConn() {
        if(conn.isEmpty()) {
            String url = "jdbc:postgresql://localhost:5432/cashier";
            String user = "cashier";
            String pw = "c45h13r456";

            try {
                Class.forName("org.postgresql.Driver");
                conn = Optional.ofNullable(DriverManager.getConnection(url, user, pw));
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
