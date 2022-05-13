package rifqimuhammadaziz.javafxdatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("main-form.fxml"));
//        stage.setTitle("Main Application");
//        stage.setScene(new Scene(root));
//        stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-form-i18n.fxml"), ResourceBundle.getBundle("language_id_id"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JavaFX");
        stage.setScene(scene);
        stage.show();
    }
}
