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

public class promozioneGUIController {
    @FXML
    private TextField nome, offerta, conto, codicePromo;
    private Promozione promozione = null;

    @FXML
    public void creaCliccato(ActionEvent actionEvent){
        String nomeText = nome.getText();
        String offertaTxt = offerta.getText();
        double contoTxt = Double.parseDouble(conto.getText());
        String codicePromoText = codicePromo.getText();
        try {
            promozione = cerca.cercaPromo(codicePromoText);


            if(promozione != null){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Promozione già esistente");
                alert.showAndWait();
            } else{
                promozione.creaPromozione(nomeText, offertaTxt, contoTxt, codicePromoText);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Creata");
                alert.setHeaderText(null);
                alert.setContentText("Promozione " + nomeText +  "\n" +"creata con successo");
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

