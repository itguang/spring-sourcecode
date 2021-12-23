package com.itguang;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

    public static void main(String[] args) {

    }

    public void update(String sql) {

        DataSource dataSource = getDataSource();

        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeQuery(sql);
        } catch (SQLException e) {
            // 连接异常 | sql 语法异常 | 数据一致性异常（主键索引冲突等）
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // 异常
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // 异常
                }
            }
        }

    }

    private DataSource getDataSource() {
        return null;
    }
}
