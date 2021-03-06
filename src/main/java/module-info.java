module com.example.courseproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.courseproject1 to javafx.fxml;
    exports com.example.courseproject1;
    exports com.example.courseproject1.Controllers;
    opens com.example.courseproject1.Controllers to javafx.fxml;
}