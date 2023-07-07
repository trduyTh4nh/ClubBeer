module com.example.project_ver1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    requires org.kordamp.bootstrapfx.core;

    requires java.sql;

    opens com.example.project_ver1 to javafx.fxml;
    exports com.example.project_ver1;
    exports com.example.project_ver1.controller;
    opens com.example.project_ver1.controller to javafx.fxml;
    exports com.example.project_ver1.class_model;
    opens com.example.project_ver1.class_model to javafx.fxml;
    exports com.example.project_ver1.model;
    opens com.example.project_ver1.model to javafx.fxml;
    exports com.example.project_ver1.constant;
    opens com.example.project_ver1.constant to javafx.fxml;
}