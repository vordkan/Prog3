import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class homeGUI {
    private AmministratoreGUIController controller;
    public homeGUI() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homeGUI.fxml"));
        try {
            Parent root = loader.load();
            controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Benvenuto");
            stage.setScene(new Scene(root, 700, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}