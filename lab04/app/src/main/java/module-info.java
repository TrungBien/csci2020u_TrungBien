module lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens lab4 to javafx.fxml;
    exports lab4;
}