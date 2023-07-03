module com.example.project_ver1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;

    requires org.kordamp.bootstrapfx.core;

    requires java.sql;

    opens com.example.project_ver1 to javafx.fxml;
    exports com.example.project_ver1;
}