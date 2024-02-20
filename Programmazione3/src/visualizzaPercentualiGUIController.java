import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;



public class visualizzaPercentualiGUIController {
    @FXML
    private TextField Nome;

    @FXML
    private TextField numeroTelefono;

    @FXML
    private void cercaCliccato(ActionEvent actionEvent) {
        String nome = Nome.getText();
        String numero = numeroTelefono.getText();

        Abbonato abbonato = cerca.cercaAbbonato(nome,numero);

        if (abbonato == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avviso");
            alert.setHeaderText(null);
            alert.setContentText("Utente non esistente.");
            alert.showAndWait();
            return;
        } else{
            int cont_ChiamatePrecedente = cerca.contChiamate(nome, numero);
            int cont_MessaggiPrecedente = cerca.contMessaggi(nome, numero);
            int cont_DatiPrecedente = cerca.contDati(nome,numero);

            int tot_chiamate = abbonato.getContaChiamate(cont_ChiamatePrecedente);
            int tot_sms = abbonato.getContaMessaggi(cont_MessaggiPrecedente);
            int tot_dati  = abbonato.getContaDati(cont_DatiPrecedente);

            int tot = tot_dati + tot_chiamate + tot_sms;

            if (tot == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Avviso");
                alert.setHeaderText(null);
                alert.setContentText("Nessuna attività registrata per l'utente.");
                alert.showAndWait();
                return;
            }
            double percentuale_chiamata = (tot_chiamate * 100) / tot;
            double percentuale_sms = (tot_sms * 100) / tot;
            double percentuale_dati = (tot_dati * 100) / tot;


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Avviso");
            alert.setHeaderText(null);

            alert.setContentText("Totale chiamate effettuate: " + percentuale_chiamata + "%\n" +
                    "Totale messaggi inviati: " + percentuale_sms + "%\n" +
                    "Totale volte di dati utilizzati: " + percentuale_dati + "%");
            alert.showAndWait();
        }
    }

    @FXML
    public void backToAmministratore(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AmministratoreGUI.fxml"));
            Parent root = loader.load();
            AmministratoreGUIController controller = loader.getController();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}