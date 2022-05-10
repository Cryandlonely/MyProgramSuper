package JDBC;

import java.sql.*;
import java.util.ResourceBundle;

public class tools {

    private static ResourceBundle bundle = ResourceBundle.getBundle("source.account");
    private static String Driver = bundle.getString("driver");
    private static String name = bundle.getString("name");
    private static String pwd = bundle.getString("password");

    static {
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String url) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, name, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
