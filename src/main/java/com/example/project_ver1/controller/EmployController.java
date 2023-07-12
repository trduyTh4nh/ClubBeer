package com.example.project_ver1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EmployController {
    @FXML
    private TableView<Map<String, String>> tableView;

    public EmployController() throws SQLException {

    }
    @FXML
    public void getData(){
        ObservableList<Map<String, String>> list = FXCollections.<Map<String,String>>observableArrayList();
        Map<String, String> item1 = new HashMap<>();
        item1.put("ID", "1");
        item1.put("Tên", "test");
        item1.put("Tuổi", "100");
        item1.put("Email", "Test");
        item1.put("SĐT", "0");
        item1.put("Password", "a2daeftqw");
        item1.put("Role", "0");
        list.add(item1);
        tableView.getItems().addAll(list);
    }
}
