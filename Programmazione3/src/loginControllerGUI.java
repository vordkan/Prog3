import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class loginControllerGUI {

    @FXML
    private TextField nome_ID;

    @FXML
    private TextField cognome_ID;

    @FXML
    private void backToHome(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homeGUI.fxml"));
            Parent root = loader.load();

            homeGUIController controller = loader.getController();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void accessoCliccato(ActionEvent actionEvent) {
        String nome = nome_ID.getText();
        String cognome = cognome_ID.getText();

        if (!nome.isEmpty() && !cognome.isEmpty()) {
            Abbonato abbonato = cerca.cercaAbbonato(nome, cognome);
            if (abbonato == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Avviso");
                alert.setHeaderText(null);

                alert.setContentText("Credenziali Errate!");
                alert.showAndWait();
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AbbonatoGUI.fxml"));
                    Parent root = loader.load();

                    AbbonatoGUIController abbonatoGUIController = loader.getController();
                    abbonatoGUIController.inizializza(abbonato);

                    Scene scene = new Scene(root);

                    Stage stage = new Stage();
                    stage.setTitle("Interfaccia Abbonato");
                    stage.setScene(scene);

                    Stage currentStage = (Stage) nome_ID.getScene().getWindow();
                    currentStage.close();

                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Inserisci le credenziali");
        }
    }
}