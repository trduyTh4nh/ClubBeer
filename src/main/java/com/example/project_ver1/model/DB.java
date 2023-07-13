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
    public boolean loginFunc(String email, String password) throws SQLException {
        ResultSet statement =  st.executeQuery(String.format("SELECT * FROM USERS WHERE email  = '%s' and password = '%s'", email, password));
        return statement.next();
    }
    public ResultSet getUser() throws SQLException {
        return st.executeQuery("SELECT * FROM USERS");
    }
    public ResultSet getUserbyKeyword(String keyword) throws SQLException {
        return st.executeQuery("SELECT * FROM USERS WHERE NAME LIKE '" + keyword + "%'");
    }
    public void removeUser(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM users WHERE id = " + String.valueOf(id));
        statement.execute();
    }
    public void editUser(User user) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(
                String.format(
                        "UPDATE users " +
                                "SET name = '%s', age = %d, email = '%s', password = '%s', phone = '%s', role = %d " +
                                "WHERE id = " + user.getId(), user.getName(), user.getAge(), user.getEmail(), user.getPassword(), user.getPhone(), user.getRole()
                )
        );
        statement.execute();
    }
}
