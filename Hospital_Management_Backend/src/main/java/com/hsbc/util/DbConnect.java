package com.hsbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
private static Connection conn;

    public static Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "jathin");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  conn;
    }
}
