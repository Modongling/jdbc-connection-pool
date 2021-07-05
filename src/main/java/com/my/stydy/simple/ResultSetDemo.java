package com.my.stydy.simple;

import java.sql.*;
import java.util.Properties;

public class ResultSetDemo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_test";
    public static void main(String[] args) {
        //光标移动--导航方法
        //查看光标移动的当前行中列的数据
        //更新当前行列中的数据
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Properties info = new Properties();
            info.setProperty("user", "root");
            info.setProperty("password", "root");
            conn = driver.connect(DB_URL, info);
            //关闭自动提交
            conn.setAutoCommit(false);
            String sql = "update employees set age = ? where id = ?";
            //指定结果集课更新且敏感
            ps = conn.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE);
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
