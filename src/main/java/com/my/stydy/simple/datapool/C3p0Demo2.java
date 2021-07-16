package com.my.stydy.simple.datapool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3p0Demo2 {
    public static ComboPooledDataSource getDataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_test");
        cpds.setUser("root");
        cpds.setPassword("root");

        // Optional Settings
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(100);

        return cpds;
    }

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            ComboPooledDataSource dataSource = C3p0Demo2.getDataSource();
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement("select id, first_name, last_name, age from employees");

            System.out.println("The Connection Object is of Class: " + connection.getClass());

            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3) +  "," + resultSet.getString(4));
            }

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
