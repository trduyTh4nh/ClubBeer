package com.huflit.clubbeer;

import java.sql.*;
import java.util.Properties;

public class DBHelper {
    private static final String PASSWORD = "1234";
    private static final String USER = "postgres";
    private static final String URL = "jdbc:postgresql://localhost/clubbeer";

    Statement st;
    PreparedStatement pst;
    Connection conn;
    public DBHelper() throws SQLException {
        String url = URL;
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        conn = DriverManager.getConnection(url, props);
        System.out.println("Connected");
        st = conn.createStatement();
    }
    public ResultSet getUsers() throws SQLException {
        return st.executeQuery("SELECT * FROM USERS");
    }
    public int insertUser(Users u) throws SQLException{
        pst = conn.prepareStatement(String.format("INSERT INTO USERS VALUES" +
                "(%d,'%s',%d,'%s','%s','%s',%d)",u.getID(), u.getName(), u.getAge(), u.getEmail(), u.getPassword(), u.getPhone(), u.getRole()));
        return pst.executeUpdate();
    }
}
