// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package DAO;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import com.mysql.cj.jdbc.Driver;


// /**
//  *
//  * @author asus
//  */
// public class ConnectDataBaseDB {
//     /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */

// //   private Connection conn;
// //   private String url;
// //   private String dbName;
// //
// //   private String userName; 
// //   private String password;
// //
// //   public static Connection getConnection()
// //   {
// //         Connection c = null;
// //       try{
// //            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver())
// //            String url="jdbc:mySQL://localhost:3306/quanliquantrasua";
// //            String username="root";
// //            String password="";
// //            
// //            c = DriverManager.getConnection(url,username,password);
// //       }
// //       catch(SQLException e)
// //               {
// //                   e.printStackTrace();
// //                   System.out.println("Khong tim thay database");
// //               }
// //      
// //     return c;
// //   }
// //   public static void closeConnection(Connection c)
// //   {
// //       try {
// //           if(c!=null)
// //               c.close();
// //       } catch (Exception e) {
// //           e.printStackTrace();
// //       }
// //   }
// //
//     private static Connection conn;
//     private static String url = "jdbc:mysql://localhost:3306/";
//     private static String dbName = "quanliquantrasua";
//     private static String driver = "com.mysql.cj.jdbc.Driver";
//     private static String userName = "root";
//     private static String password = "";

//     public ConnectDataBaseDB() throws SQLException {
//         connect();
//     }

//     public ConnectDataBaseDB(String url, String dbName, String driver, String userName, String password) {
//         this.url = url;
//         this.dbName = dbName;
//         this.driver = driver;
//         this.userName = userName;
//         this.password = password;
        
//     }
    

//     private void connect() throws SQLException {
//         if (conn != null) {
//             return;
//         }
//         try {
//             Class.forName(driver);
//         } catch (ClassNotFoundException e) {
//             throw new SQLException("Driver not found");
//         }
//         conn = DriverManager.getConnection(url + dbName, userName, password);
//     }

//     public void disconnect() throws SQLException {
//         if (conn != null) {
//             conn.close();
//             conn = null;
//         }
//     }

//     public void closeConnection(Connection c) {
//     try {
//         if (c != null)
//             c.close();
//     } catch (Exception e) {
//         e.printStackTrace();
//     }
// }
//     public void executeQuery(String sql) throws SQLException {
//         connect();
//         Statement statement = conn.createStatement();
//         statement.executeQuery(sql);
//         statement.close();
//     }

//     public void executeUpdate(String sql) throws SQLException {
//         connect();
//         Statement statement = conn.createStatement();
//         statement.executeUpdate(sql);
//         statement.close();
//     }

//     public ResultSet executeSelect(String sql) throws SQLException {
//         connect();
//         Statement statement = conn.createStatement();
//         ResultSet resultSet = statement.executeQuery(sql);
//         return resultSet;
//     }

//     public static Connection getConnection() {
//         return conn;
//     }
   

//         }

