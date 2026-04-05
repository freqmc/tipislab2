module org.example.tipislab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tipislab2 to javafx.fxml;
    opens org.example.tipislab2.controller to javafx.fxml;
    exports org.example.tipislab2;
}