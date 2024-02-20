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

public class registraAbbonatoGUIController {
    @FXML
    private TextField nome, cognome, residenza, numeroTelefono, codicePromo, conto;
    private Amministratore amministratore = new Amministratore();

    @FXML
    public void registraCliccato(ActionEvent actionEvent){
        String nomeText = nome.getText();
        String cognomeText = cognome.getText();
        String residenzaText = residenza.getText();
        String numeroTelefonoText = numeroTelefono.getText();
        String codicePromoText = codicePromo.getText();

        try {
            double contoValue = Double.parseDouble(conto.getText());
            Abbonato abbonato = cerca.cercaAbbonato(nomeText, numeroTelefonoText);

            if(abbonato != null){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Abbonato già registrato");
                alert.showAndWait();
            } else{
                Alert alert = new Alert(AlertType.CONFIRMATION);
                amministratore.registraAbbonato(nomeText, cognomeText, residenzaText, numeroTelefonoText, contoValue, codicePromoText);
                alert.setTitle("Registrato");
                alert.setHeaderText(null);
                alert.setContentText("Abbonato " + nomeText + " " + cognomeText + "\n" + "numero: " + numeroTelefonoText + " con la promozione: " + codicePromoText +  "\n" +"registrato con successo");
                alert.showAndWait();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid numeric value for Conto.");
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

