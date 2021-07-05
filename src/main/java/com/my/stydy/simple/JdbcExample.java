package com.my.stydy.simple;

import java.sql.*;
import java.util.Properties;

public class JdbcExample {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_test";
    public static void main(String[] args) {
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        //建立连接
        try(Connection conn = DriverManager.getConnection(DB_URL, info);
            //创建statement
            Statement stmt = conn.createStatement();
            //创建结果集
            ResultSet rs = stmt.executeQuery("select id, first_name, last_name, age from employees");
        ) {
            while (rs.next()) {
                System.out.println("Id:" + rs.getInt("id"));
                System.out.println("Age:" + rs.getInt("age"));
                System.out.println("First Name:" + rs.getString("first_name"));
                System.out.println("Last_name:" + rs.getString("last_name"));
                System.out.println("===================");
//                if( rs.wasNull( ) ) {
//                 处理NULL
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
