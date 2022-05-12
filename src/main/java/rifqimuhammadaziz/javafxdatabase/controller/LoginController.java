package rifqimuhammadaziz.javafxdatabase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rifqimuhammadaziz.javafxdatabase.dao.UserDaoImpl;
import rifqimuhammadaziz.javafxdatabase.util.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;

    private UserDaoImpl userDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDao = new UserDaoImpl();
    }

    @FXML
    private void loginAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if (userDao.login(username, password)) {
            ViewUtil.createAlert(Alert.AlertType.INFORMATION, "Login Success").showAndWait();
        } else {
            ViewUtil.createAlert(Alert.AlertType.ERROR, "Invalid Login").showAndWait();
        }
    }


}
