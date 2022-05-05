module rifqimuhammadaziz.javafxdatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens rifqimuhammadaziz.javafxdatabase to javafx.fxml;
    exports rifqimuhammadaziz.javafxdatabase;
}