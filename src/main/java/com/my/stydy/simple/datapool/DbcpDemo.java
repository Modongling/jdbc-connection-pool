package com.my.stydy.simple.datapool;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbcpDemo {
    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        Properties properties = new Properties();
        try {
            InputStream is = DbcpDemo.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(is);
            dataSource.setDriverClassName(properties.getProperty("DRIVER_CLASS"));
            dataSource.setUrl(properties.getProperty("URL"));
            dataSource.setUsername(properties.getProperty("USER"));
            dataSource.setPassword(properties.getProperty("PWD"));

            dataSource.setInitialSize(10);
            dataSource.setMaxTotal(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
                DataSource dataSource = DbcpDemo.getDataSource();
                connection = dataSource.getConnection();
                ps = connection.prepareStatement("select id, first_name, last_name, age from employees");
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getString(3) +  "," + rs.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
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
