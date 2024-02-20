import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AmministratoreGUI {
    private AmministratoreGUIController controller;
    public AmministratoreGUI() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AmministratoreGUI.fxml"));
        try {
            Parent root = loader.load();
            controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Pagina Admin");
            stage.setScene(new Scene(root, 700, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}