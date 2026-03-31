/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Admin
 */
import java.sql.*;

public class ConnectDataBaseDB {
    private static Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "quanliquantrasua";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String userName = "root";
    private static String password = "";

    public ConnectDataBaseDB() throws SQLException {
        connect();
    }

    public ConnectDataBaseDB(String url, String dbName, String driver, String userName, String password) {
        this.url = url;
        this.dbName = dbName;
        this.driver = driver;
        this.userName = userName;
        this.password = password;
    }

    private void connect() throws SQLException {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    return;
                }
            } catch (SQLException ex) {
                conn = null;
            }
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found");
        }
        conn = DriverManager.getConnection(url + dbName, userName, password);
    }

    public void disconnect() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    public void executeQuery(String sql) throws SQLException {
        connect();
        Statement statement = conn.createStatement();
        statement.executeQuery(sql);
        statement.close();
    }

    public void executeUpdate(String sql) throws SQLException {
        connect();
        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);
        statement.close();
    }

    public ResultSet executeSelect(String sql) throws SQLException {
        connect();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    public static Connection getConnection() {
        try {
            if (conn != null && conn.isClosed()) {
                conn = null;
            }
        } catch (SQLException e) {
            conn = null;
        }
        return conn;
    }
   
}