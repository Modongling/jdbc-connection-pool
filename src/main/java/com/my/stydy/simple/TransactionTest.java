package com.my.stydy.simple;

import java.sql.*;
import java.util.Properties;

public class TransactionTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_test";
    public static void main(String[] args) {
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");
        Connection conn = null;
        Statement stmt = null;
        //回滚点
        Savepoint savepoint = null;
        try {
            conn = DriverManager.getConnection(DB_URL, info);
            //关闭自动提交
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            savepoint = conn.setSavepoint("savePoint");
            String sql = "insert into Employees values (106, 20, 'Rita', 'Tez')";
            stmt.executeUpdate(sql);
            String sql2 = "insert into Employees values (107, 22, 'Sita', 'Tez')";
            stmt.executeUpdate(sql2);
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(savepoint);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
