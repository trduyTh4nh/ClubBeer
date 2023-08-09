package com.example.project_ver1.controller;

import com.example.project_ver1.model.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class StatisticalController implements Initializable {


    DB db = new DB();

    @FXML
    XYChart.Series<String, Integer> series1;

    @FXML
    XYChart.Series<String, Integer> series2;
    @FXML
    private BarChart<String, Integer> barchart;

    @FXML
    private ComboBox<String> id_year_first;
    @FXML
    private ComboBox<String> id_year_second;


    public StatisticalController() throws SQLException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id_year_first.setItems(FXCollections.observableArrayList("2016", "2018", "2020", "2022"));
        id_year_second.setItems(FXCollections.observableArrayList("2017", "2019", "2021", "2023"));

        String FirstY = id_year_first.getValue();
        String SecondY = id_year_second.getValue();


        try {
            addDataForTable(FirstY, SecondY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateData() {
        String FirstY = id_year_first.getValue();
        String SecondY = id_year_second.getValue();
        try {
            addDataForTable(FirstY, SecondY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteData() throws SQLException {

        series1.getData().clear();
        series2.getData().clear();

// Thêm dữ liệu mới vào dãy dữ liệu
        for (int i = 0; i < 12; i++) {
            series1.getData().add(new XYChart.Data<>("Tháng " + (i + 1), db.monthlyIncome(i + 1, Integer.parseInt(id_year_first.getValue()))));
            series2.getData().add(new XYChart.Data<>("Tháng " + (i + 1), db.monthlyIncome(i + 1, Integer.parseInt(id_year_second.getValue()))));
        }

// Xóa dữ liệu cũ khỏi biểu đồ
        barchart.getData().clear();

// Thêm dãy dữ liệu mới vào biểu đồ
        //barchart.getData().addAll(series1, series2);
    }

    public void addDataForTable(String idFirstYear, String idSecondYear) throws SQLException {
        // biểu đồ này sẽ so sánh tiền bán qua cách tháng của năm 2003 với 2004 xem sự thay đổi như thế nào từ đó đưa ra chiến lược marketing phù hợp



        series1 = new XYChart.Series();
        series2 = new XYChart.Series();

        series1.getData().clear();
        series2.getData().clear();


        if (idFirstYear == null) {

            id_year_first.setValue("2022");
            idFirstYear = "2022";

        }
        series1.setName(idFirstYear);
        if (idSecondYear == null) {
            id_year_second.setValue("2023");
            idSecondYear = "2023";
        }

        for (int i = 0; i < 12; i++) {
            if(idFirstYear == null){
                continue;
            }
            else {
                System.out.println(idFirstYear + " " + String.valueOf(db.monthlyIncome(i + 1, Integer.parseInt(idFirstYear))));
                series1.getData().add(new XYChart.Data<>("Tháng" + String.valueOf(i + 1), db.monthlyIncome(i + 1, Integer.parseInt(idFirstYear))));
            }
        }

        series2.setName(idSecondYear);

        for (int i = 0; i < 12; i++) {
            System.out.println(idSecondYear + " " + String.valueOf(db.monthlyIncome(i + 1, Integer.parseInt(idSecondYear))));
            series2.getData().add(new XYChart.Data<>("Tháng" + String.valueOf(i + 1), db.monthlyIncome(i + 1, Integer.parseInt(idSecondYear))));
        }

        barchart.getData().addAll(series1, series2);




        int tongtien = 0;

        try {
            tongtien = db.monthlyIncome(8, 2023);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        System.out.println(String.valueOf(tongtien));
    }
}