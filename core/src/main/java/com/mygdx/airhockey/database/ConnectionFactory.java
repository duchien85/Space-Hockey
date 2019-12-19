package com.mygdx.airhockey.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConnectionFactory {
    /**
     * Credentials for the database.
     */
    private static String URL =
            "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_SEMgroup45?serverTimezone="
                    + TimeZone.getDefault().getID();
    private static String DB_USERNAME = "pu_SEMgroup45";
    private static String DB_PASSWORD = "rVZRdo6MaZkz";

    /**
     * Sets up a connection with the database.
     * @return created Connection object.
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}