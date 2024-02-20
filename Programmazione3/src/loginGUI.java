import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class loginGUI extends Parent {

    private loginControllerGUI controller;

    public loginGUI() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            Parent root = loader.load();
            controller = loader.getController();
            getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public loginControllerGUI getController() {
        return controller;
    }

    public void mostra(Stage stage) {
        stage.setTitle("Pagina login");
        stage.setScene(new Scene(this, 700, 500));
        stage.show();
    }
}