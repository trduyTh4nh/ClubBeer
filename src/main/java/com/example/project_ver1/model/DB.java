package com.example.project_ver1.model;
import com.example.project_ver1.class_model.User;

import java.sql.*;
import java.util.Properties;

public class DB {
    private static final String URL = "jdbc:postgresql://localhost/clubbeer";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private Statement st;
    private Properties props;
    private Connection conn;

    public DB() throws SQLException{
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
    public boolean login(String email, String password) throws SQLException {
        ResultSet statement =  st.executeQuery(String.format("SELECT * FROM USERS WHERE email  = '%s' and password = '%s'", email, password));

        System.out.println(statement.next() ? "Thành công" : "Thất bại");
        return false;
    }
    public ResultSet getUser() throws SQLException {
        return st.executeQuery("SELECT * FROM USERS");
    }

}
