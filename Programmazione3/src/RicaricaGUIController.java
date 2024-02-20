import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class RicaricaGUIController {
    private Abbonato abbonato_attuale = null;
    private Stage stage;

    @FXML
    private TextField importo;

    public void inizializza(Abbonato abbonato) {
        this.abbonato_attuale = abbonato;
    }

    @FXML
    private void contanti(ActionEvent actionEvent) {
        double importoRicarica = Double.parseDouble(importo.getText()); // Leggi l'importo dall'interfaccia
        effettuaRicaricaConMetodo(new contanti(), importoRicarica);
    }

    @FXML
    private void cartadicredito(ActionEvent actionEvent) {
        double importoRicarica = Double.parseDouble(importo.getText()); // Leggi l'importo dall'interfaccia
        effettuaRicaricaConMetodo(new CartaDiCredito("3345697878658", 1000), importoRicarica);
    }

    @FXML
    private void cartadidebito(ActionEvent actionEvent) {
        double importoRicarica = Double.parseDouble(importo.getText()); // Leggi l'importo dall'interfaccia
        effettuaRicaricaConMetodo(new CartaDiDebito("3345697878658", 1000), importoRicarica);
    }

    private void effettuaRicaricaConMetodo(PaymentStrategy metodoPagamento, double importoRicarica) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Ricarica effettuata");
        alert.setHeaderText(null);

        abbonato_attuale.impostaMetodoPagamento(metodoPagamento);
        abbonato_attuale.effettuaRicarica(importoRicarica);

        String output = String.format("Ricarica di euro %.2f € effettuata \n" + "Nuovo conto: %.2f", importoRicarica, abbonato_attuale.getConto());
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
}


