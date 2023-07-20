package com.example.project_ver1.model;
import com.example.project_ver1.class_model.Product;
import com.example.project_ver1.class_model.Role;
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
    public void insertUser(User user) throws SQLException{
        PreparedStatement test =  conn.prepareStatement(String.format("INSERT INTO USERS (id, name, age, email, password, phone, role) VALUES " +
                "(%d, '%s', %d, '%s', '%s', '%s', '%s')",user.getId(), user.getName(), user.getAge(), user.getEmail(), user.getPassword(), user.getPhone(), user.getRole()));
        test.executeUpdate();
    }
    public boolean loginFunc(String email, String password) throws SQLException {
        ResultSet statement =  st.executeQuery(String.format("SELECT * FROM USERS WHERE email  = '%s' and password = '%s'", email, password));
        return statement.next();
    }
    public ResultSet getUser() throws SQLException {
        return st.executeQuery("SELECT * FROM USERS");
    }

    public ResultSet getProduct() throws SQLException{
        return st.executeQuery("SELECT * FROM SanPham");
    }

    public ResultSet searchProduct(String nameProduct) throws SQLException {
        return st.executeQuery("SELECT * FROM SanPham WHERE TenSP LIKE '"+ nameProduct + "%'");
    }
    public ResultSet getUserbyKeyword(String keyword) throws SQLException {
        return st.executeQuery("SELECT * FROM USERS WHERE NAME LIKE '" + keyword + "%'");
    }
    public void removeUser(int id) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM USERS WHERE ID = " + String.valueOf(id));
        statement.execute();
    }
    public void insertProduct(Product product) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(String.format("INSERT INTO SanPham  VALUES (%d, '%s', '%s', %d, %d)", product.getMaSP(), product.getTenSP(), product.getMoTa(), product.getMaLoaiSp(), product.getGia()));
        statement.execute();
    }
    public void updateProduct(Product product) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(String.format("UPDATE SanPham  SET tensp  = '%s'" +
                ", mota = '%s' " +
                ", maloai = %d " +
                ", gia = %d " +
                "WHERE masp = %d", product.getTenSP(), product.getMoTa(), product.getMaLoaiSp(), product.getGia(), product.getMaSP()));
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

    public void removeProduct(int ID) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(String.format("DELETE FROM SanPham WHERE masp = %d", ID));
        statement.execute();
    }
    public ResultSet getRoles() throws SQLException {
        return st.executeQuery("SELECT * FROM ROLE");
    }
    public Role getRole(String id) throws SQLException{
        ResultSet set = st.executeQuery("SELECT * FROM ROLE WHERE id = '" + id + "'");
        if (set.next()){
            return new Role(set.getString(1), set.getString(2), set.getString(3));
        };
        return new Role("err", "null", "null");
    }
}
