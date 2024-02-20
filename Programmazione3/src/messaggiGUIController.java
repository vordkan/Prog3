import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class messaggiGUIController {

    private Abbonato abbonato_attuale = null;

    @FXML
    private TextArea messaggiBox;
    private AbbonatoMemento statoPrecedente;

    public void inizializza(Abbonato abbonato) {
        this.abbonato_attuale = abbonato;
    }

    @FXML
    private void inviaButtonClicked(ActionEvent actionEvent) {

        String messaggio = messaggiBox.getText();
        int caratteri = messaggio.length();
        double costoMessaggioSingolo = caratteri * 0.006;

        int cont_precendente = cerca.contChiamate(abbonato_attuale.getNome(), abbonato_attuale.getNumeroTelefono());

        abbonato_attuale.setContaChiamate(cont_precendente);

        String codicePromozione = abbonato_attuale.getPianoTariffario();
        boolean promozioneSmsIllimitati = false;

        if (codicePromozione != null) {
            salvaStato();
            int messaggiTotali = abbonato_attuale.getContaMessaggi(cont_precendente);
            abbonato_attuale.setContaMessaggi(messaggiTotali + 1);
            /*-------------------------------------------------------------------------*/
            Promozione promozione = cerca.cercaPromo(codicePromozione);
            abbonato_attuale.setConto(abbonato_attuale.getConto()-costoMessaggioSingolo);

            if (promozione != null && "sms illimitati".equals(promozione.getOfferta())) {
                costoMessaggioSingolo = 0;
                promozioneSmsIllimitati = true;
            }
        }
        int cont_precendente2 = cerca.contChiamate(abbonato_attuale.getNome(), abbonato_attuale.getNumeroTelefono());
        double costoTotaleMessaggi = costoMessaggioSingolo * abbonato_attuale.getContaMessaggi(cont_precendente2);

        showWarningAlert(messaggio, caratteri, costoTotaleMessaggi, promozioneSmsIllimitati);
    }

    private void showWarningAlert(String messaggio, int lunghezzaSMS, double costoSMS, boolean promozioneSmsIllimitati) {
        double contoDopoInvioSMS = abbonato_attuale.getConto();

        String output;
        if (promozioneSmsIllimitati) {
            output = String.format("Hai scritto: %s\n" +
                            "Lunghezza messaggio: %d\n" +
                            "Costo del messaggio: %.2f €\n" +
                            "(Promozione messaggi illimitati attiva)\n" +
                            "Credito dopo l'invio del messaggio: %.2f €",
                    messaggio, lunghezzaSMS, costoSMS, contoDopoInvioSMS);
        } else {
            output = String.format("Hai scritto: %s\n" +
                            "Lunghezza messaggio: %d\n" +
                            "Costo del messaggio: %.2f €\n" +
                            "Credito dopo l'invio del messaggio: %.2f €",
                    messaggio, lunghezzaSMS, costoSMS, contoDopoInvioSMS);
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avviso");
        alert.setHeaderText(null);
        alert.setContentText(output);

        alert.showAndWait();
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

    private void salvaStato() {
        statoPrecedente = abbonato_attuale.creaMemento();
    }



    @FXML
    private void ripristinaMessaggioCliccato(ActionEvent event) {
        if (statoPrecedente != null) {
            try {
                // Ripristina lo stato precedente dell'abbonato
                abbonato_attuale.ripristinaDaMemento(statoPrecedente);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Avviso");
                alert.setHeaderText(null);
                alert.setContentText("Messaggio annullato!");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Avviso");
                alert.setHeaderText(null);
                alert.setContentText("ERRORE!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Avviso");
            alert.setHeaderText(null);
            alert.setContentText("non ci sono messaggi precedenti!");
            alert.showAndWait();
        }
    }
}
