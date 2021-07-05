package com.my.stydy.simple;

import java.sql.*;
import java.util.Properties;

public class StatementDemo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_test";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        //与编译为？防止SQL注入
        PreparedStatement ps = null;
        //执行存储过程 IN OUT INOUT
        //CallableStatement cs = null;
        try {
            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "root");
            conn = driver.connect(DB_URL, info);
            //stmt = conn.createStatement();
            //stmt.execute("") DDL动态SQL,ResultSet可以查询返回true否则为false
            //stmt.executeUpdate() insert update delete
            String sql = "update employees set age = ? where id = ?";
            ps = conn.prepareStatement(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

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
}
