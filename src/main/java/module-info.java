module demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;


    opens demo to javafx.fxml;
    exports demo;
}