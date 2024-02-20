import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Promozione {
    private String nomePromozione;
    private String offerta;
    private double costoMensile;
    private String codice;

    public Promozione(String nomePromozione, String offerta, double costoMensile, String codice) {
        this.nomePromozione = nomePromozione;
        this.offerta = offerta;
        this.costoMensile = costoMensile;
        this.codice = codice;
    }

    public double getCostoMensile() {
        return this.costoMensile;
    }

    public String getCodice() {
        return this.codice;
    }

    public String getOfferta() {
        return this.offerta;
    }

    public static Promozione creaPromozione(String nomePromozione, String offerta, double costoMensile, String codice) {
        Connection conn = null;
        Promozione promozione = null;
        try {
            conn = connessione.getConnection(); // Ottieni la connessione al database

            String query = "INSERT INTO Promozione (nome, offerta, costo, codice) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nomePromozione);
            preparedStatement.setString(2, offerta);
            preparedStatement.setDouble(3, costoMensile);
            preparedStatement.setString(4, codice);
            preparedStatement.executeUpdate();

            promozione = new Promozione(nomePromozione, offerta, costoMensile, codice);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connessione.closeConnection(conn);
        }
        return promozione;
    }
}
