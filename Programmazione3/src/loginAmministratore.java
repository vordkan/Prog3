import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class loginAmministratore extends Parent {

    private loginControllerGUI controller;

    public loginAmministratore() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginAmministratore.fxml"));
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
        stage.setTitle("Pagina login Amministratore");
        stage.setScene(new Scene(this, 700, 500));
        stage.show();
    }
}