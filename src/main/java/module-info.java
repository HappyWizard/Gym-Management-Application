module com.example.domolandproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;

    opens com.example.domolandproject to javafx.fxml;
    exports com.example.domolandproject;
}