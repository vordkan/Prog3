import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class AbbonatoGUIController implements Observer{

    @FXML
    private Label benvenutoLabel;
    @FXML
    private Label creditoLabel;
    @FXML
    private Label promozioneLabel;

    private Abbonato abbonato_attuale = null;
    public void inizializza(Abbonato abbonato) {
        abbonato_attuale = abbonato;
        Promozione promozione = cerca.cercaPromo(abbonato_attuale.getPianoTariffario());
        benvenutoLabel.setText("Benvenuto, " + abbonato_attuale.getNome() + " " + abbonato_attuale.getCognome() + "!");
        promozioneLabel.setText("Promozione attuale: " + promozione.getOfferta());

        abbonato_attuale.attachContoObserver((nuovoConto) -> update(nuovoConto));
        update(abbonato_attuale.getConto());
    }

    public void update(double nuovoConto) {
        String contoFormatted = String.format("%.2f", nuovoConto);
        creditoLabel.setText("Credito residuo: " + contoFormatted + "€");
    }

    @FXML
    private void chiamaCliccato(ActionEvent actionEvent) {

        String nome = abbonato_attuale.getNome();
        String numero= abbonato_attuale.getNumeroTelefono();

        command chiamata = new chiamata(abbonato_attuale);
        Invoker invoker = new Invoker();

        invoker.aggiungiComando(chiamata);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Avviso");
        alert.setHeaderText(null);

        if (abbonato_attuale.getConto() <= 0) {
            alert.setContentText("CREDITO INSUFFICIENTE, EFFETTUARE UNA RICARICA");
            alert.showAndWait();
        } else {
            int cont_precendente = cerca.contChiamate(nome, numero);
            invoker.effettuaChiamate(abbonato_attuale);
            abbonato_attuale.setContaChiamate(cont_precendente);

            String codicePromozione = abbonato_attuale.getPianoTariffario();
            if (codicePromozione != null) {
                Promozione promozione = cerca.cercaPromo(codicePromozione);
                if (promozione != null) {
                    String offerta = promozione.getOfferta();
                    if ("minuti illimitati".equals(offerta)) {
                        double contoDopoChiamata = abbonato_attuale.getConto();
                        int tempoChiamata = invoker.getDurata();
                        double costoChiamata = invoker.getCosto();
                        String output = String.format("La chiamata è durata: %d minuti.\n" +
                                        "Costo della chiamata: %.2f €\n" +
                                        "(Promozione minuti illimitati attiva)\n" +
                                        "Credito dopo la chiamata: %.2f €",
                                tempoChiamata, costoChiamata, contoDopoChiamata);
                        alert.setContentText(output);
                        alert.showAndWait();
                        return;
                    }
                }
            }

            double contoDopoChiamata = abbonato_attuale.getConto();
            int tempoChiamata = invoker.getDurata();
            double costoChiamata = invoker.getCosto();
            String output = String.format("La chiamata è durata: %d minuti.\n" +
                            "Costo della chiamata: %.2f €\n" +
                            "Credito dopo la chiamata: %.2f €",
                    tempoChiamata, costoChiamata, contoDopoChiamata);
            alert.setContentText(output);
            alert.showAndWait();
        }

    }

    @FXML
    private void usaDati(ActionEvent actionEvent) {
        String nome = abbonato_attuale.getNome();
        String numero= abbonato_attuale.getNumeroTelefono();

        command usoDati = new connessioneDati(abbonato_attuale);
        Invoker invoker = new Invoker();

        invoker.aggiungiComando(usoDati);

        if (abbonato_attuale.getConto() < 0) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Avviso");
            alert.setHeaderText(null);
            alert.setContentText("CREDITO INSUFFICIENTE EFFETTUARE UNA RICARICA");
            alert.showAndWait();
        } else {
            int cont_precendente = cerca.contDati(nome, numero);
            invoker.usoDati(abbonato_attuale);
            abbonato_attuale.setContaDati(cont_precendente);

            String codicePromozione = abbonato_attuale.getPianoTariffario();
            if (codicePromozione != null) {
                Promozione promozione = cerca.cercaPromo(codicePromozione);
                if (promozione != null) {
                    String offerta = promozione.getOfferta();
                    if ("dati illimitati".equals(offerta)) {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Avviso");
                        alert.setHeaderText(null);
                        double contoDopoDati = abbonato_attuale.getConto();
                        int MB = invoker.getMB();
                        double costoDati = invoker.getCostoMB();
                        String output = String.format("I dati usati sono: %d MB.\n" +
                                        "Costo utilizzo dati: %.2f €\n" +
                                        "(Promozione dati illimitati attiva)\n" +
                                        "Credito dopo i dati: %.2f €",
                                MB, costoDati, contoDopoDati);
                        alert.setContentText(output);
                        alert.showAndWait();
                        return;
                    }
                }
            }

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Avviso");
            alert.setHeaderText(null);
            double contoDopoUsoDati = abbonato_attuale.getConto();
            int MbDati = invoker.getMB();
            double costoDati = invoker.getCostoMB();
            String output = String.format("Hai utilizzato: %d MB.\n" +
                            "Costo utilizzo dati: %.2f €\n" +
                            "Credito dopo utilizzo dati: %.2f €",
                    MbDati, costoDati, contoDopoUsoDati);
            alert.setContentText(output);
            alert.showAndWait();
        }
    }


    @FXML
    private void ricaricaCliccato(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ricaricaGUI.fxml"));
            Parent root = loader.load();

            RicaricaGUIController ricaricaGUIController = loader.getController();
            ricaricaGUIController.inizializza(abbonato_attuale);

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Interfaccia Ricarica");
            stage.setScene(scene);

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cambiaCliccato(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cambiaPromoGUI.fxml"));
            Parent root = loader.load();

            cambiaPromoGUIController cambiaPromoGUIController = loader.getController();
            cambiaPromoGUIController.inizializza(abbonato_attuale);

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Interfaccia Cambio Promozione");
            stage.setScene(scene);

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void messaggiCliccato(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("messaggiGUI.fxml"));
            Parent root = loader.load();

            messaggiGUIController messaggiGUIController = loader.getController();
            messaggiGUIController.inizializza(abbonato_attuale);

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("Interfaccia Messaggi");
            stage.setScene(scene);

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}