
package com.shivam.sms.util;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final Dotenv dotenv = Dotenv.configure()
        .directory("C:/Users/ariji/OneDrive/Desktop/SMSJava/SMSJava")
        .ignoreIfMissing()
        .load();
    private static final String URL =
            dotenv.get("DB_URL", System.getenv("DB_URL"));

    private static final String USER =
            dotenv.get("DB_USERNAME", System.getenv("DB_USERNAME"));

    private static final String PASSWORD =
            dotenv.get("DB_PASSWORD", System.getenv("DB_PASSWORD"));

    public static Connection getConnection() throws SQLException {

        if (URL == null || USER == null || PASSWORD == null) {
            throw new SQLException(
                    "Database configuration is missing. Check your .env file."
            );
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
