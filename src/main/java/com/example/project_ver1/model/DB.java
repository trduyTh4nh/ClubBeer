package com.example.project_ver1.model;
import com.example.project_ver1.class_model.*;

import java.sql.*;
import java.util.ArrayList;
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

        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

    public ResultSet getUserbyID(int id) throws SQLException {
        String query = "SELECT * FROM USERS WHERE id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        return preparedStatement.executeQuery();
    }

    public ResultSet search(int id) throws SQLException {
        String query = "SELECT * FROM HoaDon WHERE id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);

        return preparedStatement.executeQuery();
    }

    public ResultSet getProduct() throws SQLException{
        return st.executeQuery("SELECT * FROM SanPham");
    }
    public ResultSet getOrder() throws SQLException{
        return st.executeQuery("SELECT * FROM HoaDon");
    }


    public ResultSet searchHoaDon(int key) throws SQLException{
        return st.executeQuery("SELECT * FROM HoaDon Where id  = ? ");
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
    public void removeHoaDon(int id) throws SQLException{
        PreparedStatement statement = conn.prepareStatement("DELETE FROM HoaDon WHERE ID = " + String.valueOf(id));
        statement.execute();
    }
    public void insertProduct(Product product) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(String.format("INSERT INTO SanPham  VALUES (%d, '%s', '%s', %d, %d)", product.getMaSP(), product.getTenSP(), product.getMoTa(), product.getMaLoaiSp(), product.getGia()));
        statement.execute();
    }
    public Order insertOrder(Order order) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(String.format("INSERT INTO HoaDon (NgayLap, TongTien, UserID) VALUES ('%s', %d, %d)", order.getNgayLap(), 0, loginDetails.getID()));
        statement.execute();
        ResultSet s = st.executeQuery("SELECT ID FROM HOADON");

        s.last();
        System.out.println(String.valueOf(s.getInt(1)));
        return new Order(s.getInt(1));
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
                                "SET name = '%s', age = %d, email = '%s', password = '%s', phone = '%s', role = '%s' " +
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
        ResultSet set = st.executeQuery("SELECT * FROM ROLE WHERE id = '" + id + "'");
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
//    public Product getProductById(int id) throws SQLException {
//        ResultSet set = st.executeQuery("SELECT * FROM SanPham WHERE MaSP = '"+ id + "'" );
//
//        if(set.next()){{
//            return new Product(set.getInt(1), set.getString(2), set.getString(3), set.getInt(4), set.getInt(5));
//        }}
//        return new Product(0, "null", "null", 0, 0);
//    }

    public ResultSet getorderDetailbyIDHoaDon(int id) throws SQLException {
        return st.executeQuery("SELECT soct, c.masp, tensp, soluong, gia * soluong as dongia, size FROM cthd c, sanpham s WHERE c.masp = s.masp AND ID = " + id);
    }

    public ResultSet getAllOrderDetail() throws SQLException {
        return st.executeQuery("SELECT SoCT, c.ID, TenSP, soluong, size, Gia\n" +
                "FROM CTHD c, SanPham s\n" +
                "WHERE c.masp = s.masp");
    }
    public void deleteOrderDetail(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM CTHD WHERE ID = " + id);
        ps.execute();
        PreparedStatement statement = conn.prepareStatement("DELETE FROM HOADON WHERE id = " + id);
        statement.execute();
    }
//    public void deleteDetailOrder(int soct) throws SQLException {
//        PreparedStatement statement = conn.prepareStatement(String.format("DELETE FROM CTHD WHERE soct = %d", soct));
//        statement.execute();
//    }
    public Product getProductById(int id) throws SQLException {
        ResultSet set = st.executeQuery("SELECT * FROM SanPham WHERE MaSP = '"+ id + "'" );

        if(set.next()){{
            return new Product(set.getInt(1), set.getString(2), set.getString(3), set.getInt(4), set.getInt(5));
        }}
        return new Product(0, "null", "null", 0, 0);
    }

    public void deleteDetailOrder(int soct) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(String.format("DELETE FROM CTHD WHERE soct = %d", soct));
        statement.execute();
    }

    public int monthlyIncome(int month, int year) throws SQLException {
        int total = 0;
        PreparedStatement statement = conn.prepareStatement("SELECT SUM(tongtien) AS total FROM hoadon WHERE EXTRACT(MONTH FROM ngaylap) = ? and EXTRACT(YEAR FROM ngaylap) = ?");
        statement.setInt(1, month);
        statement.setInt(2, year);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            total = resultSet.getInt("total"); // Lấy tổng dưới dạng số nguyên
        }

        return total;
    }

    public void updateHoaDon(int idHd, int tongGia) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE HoaDon SET TongTien = ? WHERE id = ?");
        statement.setInt(1,tongGia);
        statement.setInt(2, idHd);
        statement.execute();
    }
    public ResultSet getGiaFromHoaDon(int idhd) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT gia, soluong, size\n" +
                "FROM cthd c, sanpham s\n" +
                "WHERE c.masp = s.masp AND c.id = ?");
        statement.setInt(1, idhd);
        return statement.executeQuery();
    }
    public ResultSet getLoaiSp() throws SQLException{
        return st.executeQuery("SELECT * FROM LoaiSP");
    }
    public ResultSet getSingularLoaiSP(int loaisp) throws SQLException{
        return st.executeQuery("SELECT * FROM LOAISP WHERE maloai = " + loaisp);
    }
    public void addLoaiSP(CateProduct cate) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO loaisp VALUES (?,?,?)");
        ps.setInt(1, cate.getMaloai());
        ps.setString(2, cate.getTenSp());
        ps.setString(3, cate.getMoTa());
        ps.execute();
    }
    public void updateLoaiSP(CateProduct cate) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE loaisp SET tenloai = ?, mota = ? WHERE maloai = ?");
        ps.setString(1, cate.getTenSp());
        ps.setString(2, cate.getMoTa());
        ps.setInt(3, cate.getMaloai());
        ps.execute();
    }

//    public  getAllOrderDetail(){
//
//    }
}
