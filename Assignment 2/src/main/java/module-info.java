module com.example.assignment_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.assignment_2 to javafx.fxml;
    exports com.example.assignment_2;
}