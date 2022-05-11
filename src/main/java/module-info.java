module rifqimuhammadaziz.javafxdatabase {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.persistence;
    requires org.hibernate.orm.core;


    opens rifqimuhammadaziz.javafxdatabase to javafx.fxml;
    exports rifqimuhammadaziz.javafxdatabase;
    exports rifqimuhammadaziz.javafxdatabase.controller;
    opens rifqimuhammadaziz.javafxdatabase.controller to javafx.fxml;
}