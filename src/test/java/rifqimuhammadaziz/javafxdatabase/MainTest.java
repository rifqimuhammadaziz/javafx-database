package rifqimuhammadaziz.javafxdatabase;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class MainTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login-form.fxml"));
        stage.setTitle("Login Form Test");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testClickData1() {
        clickOn("#txtUsername").write("xenosty");
        clickOn("#txtPassword").write("xenosty");
        clickOn("#btnLogin");
        Node alert = lookup(".dialog-pane").query(); // to show alert
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Login Success");
    }

    @Test
    public void testClickData2() {
        clickOn("#txtUsername").write("xenosty");
        clickOn("#txtPassword").write("invalidpassword");
        clickOn("#btnLogin");
        Node alert = lookup(".dialog-pane").query(); // to show alert
        DialogPane pane = (DialogPane) alert;
        Assertions.assertThat(pane).isVisible();
        Assertions.assertThat(pane.getContentText()).hasToString("Invalid Login");
    }
}