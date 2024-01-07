module com.miracle.explorer {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.desktop;

    opens com.miracle.explorer to javafx.fxml;
    exports com.miracle.explorer;
}
