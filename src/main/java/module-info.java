module com.huflit.clubbeer {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.huflit.clubbeer to javafx.fxml;
    exports com.huflit.clubbeer;
}