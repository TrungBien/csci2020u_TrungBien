module com.example.lab5 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;

    opens com.example.lab5 to javafx.fxml;
    exports com.example.lab5;
}