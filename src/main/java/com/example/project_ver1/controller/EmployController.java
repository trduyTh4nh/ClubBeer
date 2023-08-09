package com.example.project_ver1.controller;

import com.example.project_ver1.class_model.Product;
import com.example.project_ver1.class_model.Role;
import com.example.project_ver1.class_model.User;
import com.example.project_ver1.model.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployController implements Initializable {
    private ArrayList<User> u;
    @FXML private ComboBox<Role> cbRole;
    private int ID;
    @FXML
    private TextField field_id, field_name, field_age, field_email, field_phone, field_role;
    @FXML
    private PasswordField field_password;
    DB db;
    @FXML
    private TableView<User> tableUser;
    @FXML
    public TableColumn<Product, Integer> col_id;
    @FXML
    public TableColumn<Product, String> col_name;
    @FXML
    public TableColumn<Product, Integer> col_age;
    @FXML
    public TableColumn<Product, String> col_email;
    @FXML
    public TableColumn<Product, String> col_phone;
    @FXML
    public TableColumn<Product, String> col_psswd;
    @FXML
    public TableColumn<Product, Integer> col_role;
    @FXML
    public TextField search_employ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getData();
            ArrayList<Role> arr = getRoles();
            ObservableList<Role> combobox = FXCollections.observableList(arr);
            cbRole.setItems(combobox);
            cbRole.getSelectionModel().select(combobox.get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public EmployController() throws SQLException {
        db = new DB();
    }

    @FXML
    public void getData() throws SQLException {
        getTableprops();
        u = userToArr();
        tableUser.getItems().addAll(u);
    }
    public void getData(ArrayList<User> u) throws SQLException {
        getTableprops();
        tableUser.getItems().addAll(u);
    }

    private void getTableprops() {
        tableUser.getItems().clear();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_psswd.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_role.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    @FXML
    public void searchUser() throws SQLException{
        String keyword = search_employ.getText();
        u = userToArr(keyword);
        getData(u);
    }
    private ArrayList<User> userToArr() throws SQLException {
        ArrayList<User> usrs = new ArrayList<>();
        ResultSet r = db.getUser();
        while(r.next()){
            usrs.add(new User(r.getInt(1), r.getString(2), r.getInt(3), r.getString(4), r.getString(5), r.getString(6), r.getString(8)));
        }
        return usrs;
    }
    private ArrayList<User> userToArr(String keyword) throws SQLException {
        ArrayList<User> usrs = new ArrayList<>();
        ResultSet r = db.getUserbyKeyword(keyword);
        while(r.next()){
            usrs.add(new User(r.getInt(1), r.getString(2), r.getInt(3), r.getString(4), r.getString(5), r.getString(6), r.getString(8)));
        }
        return usrs;
    }
    @FXML
    public void clickItem() throws SQLException {
        getItem();
    }
    @FXML
    public void keyboardItem(KeyEvent e) throws SQLException {
        if(e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.UP){
            getItem();
        }
    }

    private void getItem() throws SQLException {

        User user = tableUser.getSelectionModel().getSelectedItem();
        if(user == null){
            return;
        }
        Role r = db.getRole(user.getRole());
        ID = user.getId();
        field_name.setText(user.getName());
        field_id.setText(String.valueOf(user.getId()));
        field_id.setDisable(true);
        field_age.setText(String.valueOf(user.getAge()));
        field_email.setText(String.valueOf(user.getEmail()));
        field_password.setText(String.valueOf(user.getPassword()));
        field_phone.setText(user.getPhone());
        if(user.getRole() != "null"){
            cbRole.getSelectionModel().select(r);
        }
    }
    @FXML
    public void clearBoxes() throws SQLException {
        field_name.setText("");
        field_id.setText("");
        field_id.setDisable(false);
        field_age.setText("");
        field_email.setText(String.valueOf(""));
        field_password.setText(String.valueOf(""));
        field_phone.setText("");
        cbRole.getSelectionModel().select(db.getRole("nhanv"));
    }
    public User getFromField(){
        User user = new User();
        user.setId(Integer.parseInt(field_id.getText()));
        user.setName(field_name.getText());
        user.setAge(Integer.parseInt(field_age.getText()));
        user.setEmail(field_email.getText());
        user.setPassword(field_password.getText());
        user.setPhone(field_phone.getText());
        user.setRole(cbRole.getSelectionModel().getSelectedItem().getId());
        return user;
    }
    @FXML
    public void editUser() {
        User u = new User();
        u.setId(Integer.parseInt(field_id.getText()));
        u.setName(field_name.getText());
        u.setPhone(field_phone.getText());
        u.setPassword(field_password.getText());
        u.setEmail(field_email.getText());
        u.setAge(Integer.parseInt(field_age.getText()));
        u.setRole(cbRole.getSelectionModel().getSelectedItem().getId());
        try{
            db.editUser(u);
            getData();
        } catch (SQLException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Lỗi khi sửa người dùng");
            a.setContentText(e.getMessage());
            a.show();
        }

    }
    @FXML
    public void addUser() throws SQLException {
        try{
            User user = getFromField();
            db.insertUser(user);
            getData();
        } catch (SQLException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi CSDL");
            a.setContentText("Lỗi khi truy xuất CSDL: \n" + e.getMessage());
            a.setHeaderText("Lỗi SQL");
            a.show();
        }
        clearBoxes();
    }
    @FXML
    public void deleteUser() throws SQLException{
        try{
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Xác nhận xoá");
            a.setContentText("Bạm có muốn xoá người dùng " + String.valueOf(ID) + "?");
            a.setHeaderText("Xoá người dùng?");
            a.showAndWait();
            if(a.getResult() == ButtonType.OK){
                db.removeUser(ID);
                getData();
            }
        } catch (SQLException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi CSDL");
            a.setContentText("Lỗi khi truy xuất CSDL: \n" + e.getMessage());
            a.setHeaderText("Lỗi SQL");
            a.show();
        }
    }
    private ArrayList<Role> getRoles() throws SQLException {
        ArrayList<Role> roles = new ArrayList<>();
        ResultSet r = db.getRoles();
        while (r.next()){
            roles.add(new Role(r.getString(1), r.getString(2), r.getString(3)));
        }
        return roles;
    }

}
