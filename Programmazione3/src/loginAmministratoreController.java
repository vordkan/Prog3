import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginAmministratoreController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void backToHome(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homeGUI.fxml"));
            Parent root = loader.load();

            homeGUIController controller = loader.getController(); // Ottenere il controller corretto

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void accessoCliccato(ActionEvent event) {
        String nome = nomeTextField.getText();
        String password = passwordField.getText();

        if (nome.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avviso");
            alert.setHeaderText(null);
            alert.setContentText("Inserisci le credenziali!");
            alert.showAndWait();
        } else if (nome.equals("amministratore") || password.equals("amministratore")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AmministratoreGUI.fxml"));
                Parent root = loader.load();

                AmministratoreGUIController amministratoreGUIController = loader.getController();

                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setTitle("Interfaccia Amministratore");
                stage.setScene(scene);

                Stage currentStage = (Stage) nomeTextField.getScene().getWindow();
                currentStage.close();

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Avviso");
            alert.setHeaderText(null);
            alert.setContentText("Credenziali non valide!");
            alert.showAndWait();

        }
    }
}
