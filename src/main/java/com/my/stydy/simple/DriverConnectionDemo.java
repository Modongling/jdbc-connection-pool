package com.my.stydy.simple;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DriverConnectionDemo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_test";
    public static void main(String[] args) {
        //反射
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")/*.newInstance() JVM不合法*/;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        }

        //DriverManager
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Connection
        Connection conn = null;
        try {
            //conn = DriverManager.getConnection(DB_URL);
            //conn =DriverManager.getConnection(DB_URL, "root", "root");
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "root");
            conn = DriverManager.getConnection(DB_URL, info);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
