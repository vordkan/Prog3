
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Amministratore {
    public Abbonato registraAbbonato(String nome, String cognome, String residenza, String numeroTelefono, double conto, String codicepromo) {
        Connection conn = null;
        Abbonato abbonato = null;
        try {
            conn = connessione.getConnection();

            String query = "INSERT INTO Abbonato (Nome, Cognome, Residenza, NumeroTelefono, Conto, codicepromo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cognome);
            preparedStatement.setString(3, residenza);
            preparedStatement.setString(4, numeroTelefono);
            preparedStatement.setDouble(5, conto);
            preparedStatement.setString(6, codicepromo);
            preparedStatement.executeUpdate();

            abbonato = new Abbonato(nome, cognome, residenza, numeroTelefono, codicepromo, conto);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connessione.closeConnection(conn); // Chiudi la connessione utilizzando il metodo closeConnection() della classe Connessione
        }
        return abbonato;
    }
}
