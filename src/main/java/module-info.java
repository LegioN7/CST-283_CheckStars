module org.example.checkstars {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.checkstars to javafx.fxml;
    exports org.example.checkstars;
}