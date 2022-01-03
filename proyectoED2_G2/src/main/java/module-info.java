module com.mycompany.proyectoed2_g2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.proyectoed2_g2 to javafx.fxml;
    exports com.mycompany.proyectoed2_g2;
}
