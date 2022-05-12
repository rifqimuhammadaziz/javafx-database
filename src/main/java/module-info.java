module rifqimuhammadaziz.javafxdatabase {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens rifqimuhammadaziz.javafxdatabase to javafx.fxml;
    exports rifqimuhammadaziz.javafxdatabase;
    exports rifqimuhammadaziz.javafxdatabase.controller;
    opens rifqimuhammadaziz.javafxdatabase.controller to javafx.fxml;
    opens rifqimuhammadaziz.javafxdatabase.hibernateEntity to javafx.fxml, org.hibernate.orm.core;
    exports rifqimuhammadaziz.javafxdatabase.hibernateEntity;
}