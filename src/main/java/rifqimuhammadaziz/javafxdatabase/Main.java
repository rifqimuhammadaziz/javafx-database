package rifqimuhammadaziz.javafxdatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("main-form.fxml"));
//        stage.setTitle("Main Application");
//        stage.setScene(new Scene(root));
//        stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("JavaFX Data Sharing");
        stage.setScene(scene);
        stage.show();
    }
}
