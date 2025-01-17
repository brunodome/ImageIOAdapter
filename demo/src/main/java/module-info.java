module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;

    opens unical.demacs to javafx.fxml;
    opens unical.demacs.Controller to javafx.fxml;
    exports unical.demacs.Controller to javafx.fxml;
    exports unical.demacs;
}