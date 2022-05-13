package rifqimuhammadaziz.javafxdatabase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginI18NController implements Initializable {
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeUILanguage(resourceBundle);
    }

    @FXML
    private void loginAction(ActionEvent actionEvent) {

    }

    private void changeUILanguage(ResourceBundle rb) {
        lblUsername.setText(rb.getString("label.username"));
        lblPassword.setText(rb.getString("label.password"));
        btnLogin.setText(rb.getString("button.login"));
    }
}
