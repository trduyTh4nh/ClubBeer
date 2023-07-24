package com.example.project_ver1.model;
import com.example.project_ver1.class_model.*;

import java.sql.*;
import java.util.Properties;

public class DB {
    private static final String URL = "jdbc:postgresql://localhost/clubbeer";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private Statement st;
    private Properties props;
    private Connection conn;
    private  LoginDetails loginDetails = LoginDetails.INSTANCE;

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
    public void insertOrder(Order order) throws SQLException {

        PreparedStatement statement = conn.prepareStatement(String.format("INSERT INTO HoaDon VALUES (%d, '%s', %d, %d)", order.getMaHD(), order.getNgayLap(), 0, loginDetails.getID()));
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
    public Role getRoleByEmail(String email) throws SQLException{
            ResultSet set = st.executeQuery("SELECT * FROM ROLE r, USERS u WHERE u.role = r.id and u.email = '" + email + "'");
        if (set.next()){
            return new Role(set.getString(1), set.getString(2), set.getString(3));
        };
        return new Role("err", "null", "null");
    }
    
    public User getUserByEmail(String email) throws SQLException {
        ResultSet set = st.executeQuery("SELECT * FROM USERS WHERE email ='" + email + "'");
        set.next();
        return new User(set.getInt(1), set.getString(2), set.getInt(3), set.getString(4), set.getString(5), set.getString(6), set.getString(8));
    }
    public Role getRole(String id) throws SQLException{
        ResultSet set = st.executeQuery("SELECT * FROM ROLE WHERE id; = '" + id + "'");
        if (set.next()){
            return new Role(set.getString(1), set.getString(2), set.getString(3));
        };
        return new Role("err", "null", "null");
    }
    public void inserdOrderDetail(OrderDeltail orderDeltail) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(String.format("INSERT INTO CTHD (ID, MaSP, soluong, size) VALUES (%d, %d, %d, '%s')", orderDeltail.getIdHD(),orderDeltail.getIdsp(),orderDeltail.getSoluong(), orderDeltail.getSize()));
        statement.execute();
    }

    public ResultSet getOrderdetail() throws SQLException {
        return st.executeQuery("SELECT * FROM CTHD");
    }

//    public  getAllOrderDetail(){
//
//    }
}
