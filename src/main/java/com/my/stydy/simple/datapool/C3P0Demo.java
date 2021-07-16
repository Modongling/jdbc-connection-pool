package com.my.stydy.simple.datapool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class C3P0Demo {
    public static void init() {
        ComboPooledDataSource cpds = null;
        try {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_test");
            cpds.setUser("root");
            cpds.setPassword("root");
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMinPoolSize(20);
            System.out.println(cpds);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            if (cpds != null) {
                cpds.close();
            }
        }
    }

    public static void factoryInit() {
        DataSource dataSource = null;
        DataSource dsWithConfig = null;
        Connection conn = null;
        try {
             dataSource = DataSources.unpooledDataSource("jdbc:mysql://localhost:3306/jdbc_test",
                    "root", "root");
            Map<String, Object> config = new HashMap<>();
            config.put("maxStatements", "200");
            config.put("maxPoolSize", new Integer(50));
            dsWithConfig = DataSources.pooledDataSource(dataSource, config);
            conn = dsWithConfig.getConnection();
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

    public static void main(String[] args) {
        //init();
        factoryInit();
    }
}
