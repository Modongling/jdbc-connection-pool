package com.my.stydy.simple;

import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class BatchingTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_test";
    public static void main(String[] args) {
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, info);
            String sql = "insert into Employees(id, age, first_name, last_name) values (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            stmt.setInt(1, 400);
            stmt.setInt(2, 33);
            stmt.setString(3, "Pappu");
            stmt.setString(4, "Singh");
            stmt.addBatch();

            stmt.setInt(1, 401);
            stmt.setInt(2, 31);
            stmt.setString(3, "Pawan");
            stmt.setString(4, "Singh");
            stmt.addBatch();

            int[] counts = stmt.executeBatch();
            System.out.println("总共提交:" + counts.length);
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
