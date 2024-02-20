import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Abbonato extends Subject {
    private String nome;
    private String cognome;
    private String residenza;
    private String numeroTelefono;
    private String pianoTariffario;
    private double conto;
    private PaymentStrategy carta;
    private int contaChiamate;
    private int contaMessaggi;
    private int contaDati;

    // CREAZIONE COSTRUTTORE
    public Abbonato(String nome, String cognome, String residenza, String numeroTelefono, String pianoTariffario, double conto) {
        this.nome = nome;
        this.cognome = cognome;
        this.residenza = residenza;
        this.numeroTelefono = numeroTelefono;
        this.pianoTariffario = pianoTariffario;
        this.conto = conto;
    }

    // RIPRISTINO MEMENTO
    public Abbonato ripristinaDaMemento(AbbonatoMemento memento) {
        this.nome = memento.getNome();
        this.cognome = memento.getCognome();
        this.residenza = memento.getResidenza();
        this.numeroTelefono = memento.getNumeroTelefono();
        this.pianoTariffario = memento.getPianoTariffario();
        this.conto = memento.getConto();
        this.contaChiamate = memento.getContaChiamate();
        
        return this;
    }

/*-------------------------------------------------------------------------------------------------------------------*/
    // METODI SET
    public void setContaChiamate(int cont_precedente) {
        this.contaChiamate = cont_precedente;
        this.contaChiamate++;

        Connection conn = null;
        try {
            conn = connessione.getConnection();
            String query = "UPDATE Abbonato SET contChiamate = ? WHERE Nome = ? AND NumeroTelefono = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, this.contaChiamate);
            preparedStatement.setString(2, this.nome);
            preparedStatement.setString(3, this.numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setRipristinaContaChiamate(int nuovoContatore) {
        this.contaChiamate = nuovoContatore;

        Connection conn = null;
        try {
            conn = connessione.getConnection();
            String query = "UPDATE Abbonato SET contChiamate = ? WHERE Nome = ? AND NumeroTelefono = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, this.contaChiamate);
            preparedStatement.setString(2, this.nome);
            preparedStatement.setString(3, this.numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setContaMessaggi(int cont_precedente){
        this.contaMessaggi = cont_precedente;
        this.contaMessaggi++;

        Connection conn = null;
        try {
            conn = connessione.getConnection();
            String query = "UPDATE Abbonato SET contMessaggi = ? WHERE Nome = ? AND NumeroTelefono = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, this.contaMessaggi);
            preparedStatement.setString(2, this.nome);
            preparedStatement.setString(3, this.numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setRipristinaContaMessaggi(int nuovoContatore) {
        this.contaMessaggi = nuovoContatore;

        Connection conn = null;
        try {
            conn = connessione.getConnection();
            String query = "UPDATE Abbonato SET contMessaggi = ? WHERE Nome = ? AND NumeroTelefono = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, this.contaMessaggi); // Utilizza this.contaMessaggi invece di this.contaChiamate
            preparedStatement.setString(2, this.nome);
            preparedStatement.setString(3, this.numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setContaDati(int cont_precedente){
        this.contaDati = cont_precedente;
        this.contaDati++;

        Connection conn = null;
        try {
            conn = connessione.getConnection();
            String query = "UPDATE Abbonato SET contDati = ? WHERE Nome = ? AND NumeroTelefono = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, this.contaDati);
            preparedStatement.setString(2, this.nome);
            preparedStatement.setString(3, this.numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setRipristinaContaDati(int cont_precedente) {
        this.contaDati = cont_precedente;

        Connection conn = null;
        try {
            conn = connessione.getConnection();
            String query = "UPDATE Abbonato SET contDati = ? WHERE Nome = ? AND NumeroTelefono = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, this.contaDati);
            preparedStatement.setString(2, this.nome);
            preparedStatement.setString(3, this.numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void setNome(String nome) {
        Connection conn = null;
        try{
            conn = connessione.getConnection();
            String query = "UPDATE Abbonato SET Nome = Nome + ? WHERE Nome = ? AND NumeroTelefono = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, this.nome);
            preparedStatement.setString(2, this.nome);
            preparedStatement.setString(3, this.numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCodicePromo(String nuovoCodicePromo) {
        Connection conn = null;
        try {
            conn = connessione.getConnection();
            String query = "UPDATE Abbonato SET codicePromo = ? WHERE Nome = ? AND NumeroTelefono = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nuovoCodicePromo);
            preparedStatement.setString(2, this.nome);
            preparedStatement.setString(3, this.numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.pianoTariffario = nuovoCodicePromo;
    }


    public void setConto(double nuovoConto) {
        Connection conn = null;
        try {
            conn = connessione.getConnection();
            String query = "UPDATE Abbonato SET conto = ? WHERE Nome = ? AND NumeroTelefono = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setDouble(1, nuovoConto);
            preparedStatement.setString(2, this.nome);
            preparedStatement.setString(3, this.numeroTelefono);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.conto = nuovoConto;
        notifyContoObservers(nuovoConto); // Notifica gli observer del conto dopo averlo aggiornato
    }

/*-------------------------------------------------------------------------------------------------------------------*/
    // METODI GET
    public String getNome() { return this.nome; }

    public String getCognome() { return this.cognome; }

    public String getNumeroTelefono() { return this.numeroTelefono; }

    public String getPianoTariffario() { return this.pianoTariffario; }

    public double getConto() { return this.conto; }

    public int getContaChiamate(int cont_precedente) {
        this.contaChiamate = cont_precedente;
        return this.contaChiamate;
    }
    public int getContaMessaggi(int cont_precedente){
        this.contaMessaggi = cont_precedente;
        return this.contaMessaggi; }
    public int getContaDati(int cont_precedente){
        this.contaDati = cont_precedente;
        return this.contaDati;
    }

/*--------------------------------------------------------------------------------------------------------------------*/
    // IMPOSTAZIONE METODO DI PAGAMENTO
    public void impostaMetodoPagamento(PaymentStrategy metodoPagamento) { this.carta = metodoPagamento; }


    // FUNZIONE PER EFFETTUARE LA RICARICA
    public void effettuaRicarica(double importoRicarica) {
        Connection conn = null;
        try{
            conn = connessione.getConnection();
            if (this.carta != null) {
                boolean ricarica = this.carta.pay(importoRicarica);
                if (ricarica) {
                    this.conto += importoRicarica;

                    String query = "UPDATE Abbonato SET Conto = Conto + ? WHERE Nome = ? AND NumeroTelefono = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setDouble(1, importoRicarica);
                    preparedStatement.setString(2, this.nome);
                    preparedStatement.setString(3, this.numeroTelefono);
                    preparedStatement.executeUpdate();

                } else {
                }
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*-------------------------------------------------------------------------------------------------------------------*/
    public AbbonatoMemento creaMemento() {
        return new AbbonatoMemento(nome, cognome, residenza, numeroTelefono, pianoTariffario, conto, contaChiamate, contaMessaggi, contaDati);
    }
}