module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}