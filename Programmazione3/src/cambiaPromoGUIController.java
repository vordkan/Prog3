import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.List;

public class cambiaPromoGUIController {
    @FXML
    private ChoiceBox<String> selezioneMenu;

    private Abbonato abbonato_attuale = null;

    public void inizializza(Abbonato abbonato) {
        this.abbonato_attuale = abbonato;

        // Popola la ChoiceBox con i nomi delle offerte delle promozioni disponibili nel database
        List<Promozione> promozioniDisponibili = cerca.getPromozioniDisponibili();
        for (Promozione promozione : promozioniDisponibili) {
            selezioneMenu.getItems().add(promozione.getOfferta());
        }
    }

    @FXML
    private void cambiaCliccato(ActionEvent actionEvent) {
        String nomeOffertaSelezionata = selezioneMenu.getValue();

        Promozione promozioneSelezionata = cerca.cercaPromoPerOfferta(nomeOffertaSelezionata);

        if (promozioneSelezionata != null) {
            abbonato_attuale.setCodicePromo(promozioneSelezionata.getCodice());
            cerca.aggiornaCodicePromoAbbonato(abbonato_attuale.getNumeroTelefono(), promozioneSelezionata.getCodice());

            Alert successAlert = new Alert(AlertType.INFORMATION);
            successAlert.setTitle("Successo");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Promozione cambiata con successo.");
            successAlert.showAndWait();
        } else {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setTitle("Errore");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("La promozione selezionata non è stata trovata.");
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void backToAbbonatoGUI(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AbbonatoGUI.fxml"));
            Parent root = loader.load();

            AbbonatoGUIController controller = loader.getController();
            controller.inizializza(abbonato_attuale);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
