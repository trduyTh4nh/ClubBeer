package com.example.project_ver1;
import java.sql.*;
import java.util.Properties;

public class DBHelper {
    private static final String URL = "jdbc:postgresql://localhost/clubbeer";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    Statement st;
    Properties props;
    Connection conn;

    public DBHelper() throws SQLException{
        props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        conn = DriverManager.getConnection(URL,props);

        st = conn.createStatement();
    }
    public int insertUser(User user) throws SQLException{
        PreparedStatement test =  conn.prepareStatement(String.format("INSERT INTO USERS VALUES " +
                "(%d, '%s', %d, '%s', '%s', '%s' , %d)",user.getId(), user.getName(), user.getAge(), user.getEmail(), user.getPassword(), user.getPhone(), user.getRole()));
        return test.executeUpdate();
    }
    public ResultSet getUser() throws SQLException {
        return st.executeQuery("SELECT * FROM USERS");
    }

}
