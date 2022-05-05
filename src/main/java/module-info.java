module rifqimuhammadaziz.javafxdatabase {
    requires javafx.controls;
    requires javafx.fxml;


    opens rifqimuhammadaziz.javafxdatabase to javafx.fxml;
    exports rifqimuhammadaziz.javafxdatabase;
}