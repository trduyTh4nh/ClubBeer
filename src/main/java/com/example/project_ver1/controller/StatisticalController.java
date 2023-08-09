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
import java.util.ResourceBundle;


public class StatisticalController implements Initializable {


    DB db = new DB();

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
        id_year_first.setItems(FXCollections.observableArrayList("2022", "2023"));
        id_year_second.setItems(FXCollections.observableArrayList("2022", "2023"));

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

    public void addDataForTable(String idFirstYear, String idSecondYear) throws SQLException {
        // biểu đồ này sẽ so sánh tiền bán qua cách tháng của năm 2003 với 2004 xem sự thay đổi như thế nào từ đó đưa ra chiến lược marketing phù hợp
        XYChart.Series<String, Integer> series1 = new XYChart.Series();
//        series1.getData().add(new XYChart.Data("Tháng 1", 200000));
//        series1.getData().add(new XYChart.Data("Tháng 2", 380000));
//        series1.getData().add(new XYChart.Data("Tháng 3", 240000));
//        series1.getData().add(new XYChart.Data("Tháng 4", 130000));
//        series1.getData().add(new XYChart.Data("Tháng 5", 500000));
//        series1.getData().add(new XYChart.Data("Tháng 6", 700000));
//        series1.getData().add(new XYChart.Data("Tháng 7", 900000));
//        series1.getData().add(new XYChart.Data("Tháng 8", 300000));
//        series1.getData().add(new XYChart.Data("Tháng 9", 203000));
//        series1.getData().add(new XYChart.Data("Tháng 10", 240000));
//        series1.getData().add(new XYChart.Data("Tháng 11", 600060));
//        series1.getData().add(new XYChart.Data("Tháng 12", 200300));

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
            System.out.println(idFirstYear + " " + String.valueOf(db.monthlyIncome(i + 1, Integer.parseInt(idFirstYear))));
            series1.getData().add(new XYChart.Data<>("Tháng" + String.valueOf(i + 1), db.monthlyIncome(i + 1, Integer.parseInt(idFirstYear))));
        }


        XYChart.Series series2 = new XYChart.Series();
        series2.setName(idSecondYear);

        for (int i = 0; i < 12; i++) {

            System.out.println(idSecondYear + " " + String.valueOf(db.monthlyIncome(i + 1, Integer.parseInt(idSecondYear))));
            series2.getData().add(new XYChart.Data<>("Tháng" + String.valueOf(i + 1), db.monthlyIncome(i + 1, Integer.parseInt(idSecondYear))));
        }
//        series2.getData().add(new XYChart.Data("Tháng 1", 600000));
//        series2.getData().add(new XYChart.Data("Tháng 2", 480000));
//        series2.getData().add(new XYChart.Data("Tháng 3", 40000));
//        series2.getData().add(new XYChart.Data("Tháng 4", 130000));
//        series2.getData().add(new XYChart.Data("Tháng 5", 80000));
//        series2.getData().add(new XYChart.Data("Tháng 6", 200000));
//        series2.getData().add(new XYChart.Data("Tháng 7", 400000));
//        series2.getData().add(new XYChart.Data("Tháng 8", 600000));
//        series2.getData().add(new XYChart.Data("Tháng 9", 203000));
//        series2.getData().add(new XYChart.Data("Tháng 10", 240000));
//        series2.getData().add(new XYChart.Data("Tháng 11", 600060));
//        series2.getData().add(new XYChart.Data("Tháng 12", 200300));

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
