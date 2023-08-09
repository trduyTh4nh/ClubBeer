package com.example.project_ver1.controller;

import com.example.project_ver1.class_model.CateProduct;
import com.example.project_ver1.class_model.Product;
import com.example.project_ver1.model.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    public TableView<Product> tableProduct;
    public TableColumn<Product, Integer> masp;
    public TableColumn<Product, String> tensp;
    public TableColumn<Product, String> motasp;
    public TableColumn<Product, String> loaisp;
    public TableColumn<Product, String> giasp;
    @FXML
    ComboBox<CateProduct> cbLoaiSP;
    @FXML
    TextField id_idcate;
    @FXML
    TextField id_namecate;
    @FXML
    TextField id_desccate;
    @FXML
    Button btn_addCate;
    @FXML
    Button btn_delCate;
    @FXML
    Button btn_editCate;

    @FXML
    TextField id_id;

    @FXML
    TextField id_name;


    @FXML
    TextField id_price;

    @FXML
    TextField id_desc;

    @FXML
    TextField id_search;
    DB db = new DB();

    private int ID;

    public ProductController() throws SQLException {

    }

    ArrayList<CateProduct> arrcate;
    @FXML
    public void chooseCate(){
        CateProduct cate = cbLoaiSP.getSelectionModel().getSelectedItem();
        id_idcate.setText(String.valueOf(cate.getMaloai()));
        id_namecate.setText(cate.getTenSp());
        id_desccate.setText(cate.getMoTa());
    }
    @FXML
    public void addCate() throws SQLException {
        int idCate = Integer.parseInt(id_idcate.getText());
        String nameCate = id_namecate.getText();
        String mota = id_desccate.getText();
        CateProduct cate = new CateProduct(idCate, nameCate, mota);
        try{
            arrcate.clear();
            db.addLoaiSP(cate);
            id_idcate.setText("");
            id_namecate.setText("");
            id_desccate.setText("");
            ResultSet rs = db.getLoaiSp();
            while (rs.next()){

                arrcate.add(new CateProduct(rs.getInt("maloai"), rs.getString("tenloai"), rs.getString("mota")));
            }
            ObservableList<CateProduct> ct = FXCollections.observableArrayList(arrcate);
            cbLoaiSP.setItems(ct);
        } catch (SQLException e){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Lỗi truy xuất CSDL");
            a.setContentText(e.getMessage());
            a.show();
        }

    }
    @FXML
    public void updateCate(){
        int idCate = Integer.parseInt(id_idcate.getText());
        String nameCate = id_namecate.getText();
        String mota = id_desccate.getText();
        CateProduct cate = new CateProduct(idCate, nameCate, mota);
        try{
            arrcate.clear();
            db.updateLoaiSP(cate);
            id_idcate.setText("");
            id_namecate.setText("");
            id_desccate.setText("");
            ResultSet rs = db.getLoaiSp();
            while (rs.next()){
                arrcate.add(new CateProduct(rs.getInt("maloai"), rs.getString("tenloai"), rs.getString("mota")));
            }
            ObservableList<CateProduct> ct = FXCollections.observableArrayList(arrcate);
            cbLoaiSP.setItems(ct);
        } catch (SQLException e){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Lỗi truy xuất CSDL");
            a.setContentText(e.getMessage());
            a.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            arrcate = new ArrayList<>();
            ResultSet rs = db.getLoaiSp();
            while (rs.next()){
                arrcate.add(new CateProduct(rs.getInt("maloai"), rs.getString("tenloai"), rs.getString("mota")));
            }
            ObservableList<CateProduct> cate = FXCollections.observableArrayList(arrcate);
            cbLoaiSP.setItems(cate);
            getData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void addProduct() throws SQLException {
        int idCate = cbLoaiSP.getSelectionModel().getSelectedItem().getMaloai();
        Product product = new Product(Integer.parseInt(id_id.getText().toString()), id_name.getText().toString(), id_desc.getText().toString(), idCate, Integer.parseInt(id_price.getText().toString()));
        db.insertProduct(product);
        getData();

        clearTextFeild();
    }
    @FXML
    public void clearFields(){
        clearTextFeild();
        id_idcate.setText("");
        id_namecate.setText("");
        id_desccate.setText("");
    }
    public void clearTextFeild(){
        id_id.clear();
        id_name.clear();
        id_desc.clear();
        cbLoaiSP.getSelectionModel().clearSelection();
        id_price.clear();
    }
    @FXML
    public ArrayList<Product> listData() throws SQLException {
        ArrayList<Product> arrProduct = new ArrayList<>();

        ResultSet r = db.getProduct();
        while (r.next()){
            arrProduct.add(new Product(r.getInt(1),r.getString(2), r.getString(3), r.getInt(4), r.getInt(5)));
        }
        return arrProduct;
    }

    @FXML
    public ArrayList<Product> listDataToSearch() throws SQLException {
        String key = id_search.getText().toString();
        ArrayList<Product> products = new ArrayList<>();
        ResultSet r = db.searchProduct(key);
        while (r.next()){
            products.add(new Product(r.getInt(1),r.getString(2), r.getString(3), r.getInt(4), r.getInt(5)));
        }
        return products;
    }

    public void getData() throws SQLException {
        initData();

        ArrayList<Product> list = listData();
        tableProduct.getItems().addAll(list);
    }
    public void getDataTable(ArrayList<Product> arrayList){
        initData();
        tableProduct.getItems().addAll(arrayList);
    }

    private void initData() {
        tableProduct.getItems().clear();
        masp.setCellValueFactory(new PropertyValueFactory<>("MaSP"));
        tensp.setCellValueFactory(new PropertyValueFactory<>("TenSP"));
        motasp.setCellValueFactory(new PropertyValueFactory<>("MoTa"));
        loaisp.setCellValueFactory(new PropertyValueFactory<>("MaLoaiSp"));
        giasp.setCellValueFactory(new PropertyValueFactory<>("Gia"));
    }

    @FXML
    private void ClickItems() throws SQLException {
            if(tableProduct.getSelectionModel().getSelectedItem() != null){
                ID = tableProduct.getSelectionModel().getSelectedItem().getMaSP();
                String tensp = tableProduct.getSelectionModel().getSelectedItem().getTenSP();
                String masp = String.valueOf(tableProduct.getSelectionModel().getSelectedItem().getMaSP());
                String mota = tableProduct.getSelectionModel().getSelectedItem().getMoTa();
                String loaiSp = String.valueOf(tableProduct.getSelectionModel().getSelectedItem().getMaLoaiSp());
                String gia = String.valueOf(tableProduct.getSelectionModel().getSelectedItem().getGia());
                ResultSet rs = db.getSingularLoaiSP(Integer.parseInt(loaiSp));
                rs.next();
                CateProduct cate = new CateProduct(rs.getInt("maloai"), rs.getString("tenloai"), rs.getString("mota"));
                id_id.setText(masp);
                id_name.setText(tensp);
                id_desc.setText(mota);
                cbLoaiSP.getSelectionModel().select(cate);
                id_price.setText(gia);

            }

    }

    @FXML
    private void searchProduct() throws SQLException {
        initData();
        ArrayList<Product> list = listDataToSearch();
        tableProduct.getItems().addAll(list);
    }

    @FXML
    private void edtProduct() throws SQLException {
        int cate = cbLoaiSP.getSelectionModel().getSelectedItem().getMaloai();
        Product product = new Product(Integer.parseInt(id_id.getText().toString()), id_name.getText().toString(), id_desc.getText().toString(), cate, Integer.parseInt(id_price.getText().toString()));
        db.updateProduct(product);
        getData();
    }
    @FXML
    public void deleteProduct(){
        try{
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Xác nhận xoá");
            a.setContentText("Bạm có muốn sản phẩm \"" + id_name.getText() + "\"?");
            a.setHeaderText("Xoá sản phẩm?");
            a.showAndWait();
            if(a.getResult() == ButtonType.OK){
                db.removeProduct(ID);
                getData();
            }
        } catch (SQLException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi CSDL");
            a.setContentText("Lỗi khi truy xuất CSDL: \n" + e.getMessage());
            a.setHeaderText("Lỗi SQL");
            a.show();
        } catch (Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi");
            a.setContentText("Lỗi về phần mềm, có thể các ô có thể rỗng hoặc sai kiểu dữ liệu: \n" + e.getMessage());
            a.setHeaderText("Lỗi client");
            a.show();
        }

        clearTextFeild();
    }



}
