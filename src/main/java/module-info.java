module studio.fuji {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;

    opens studio.fuji to javafx.fxml;

    exports studio.fuji;
}
