import javafx.scene.Scene;

public class AbbonatoMemento {
    private String nome;
    private String cognome;
    private String residenza;
    private String numeroTelefono;
    private String pianoTariffario;
    private double conto;
    private int contaChiamate;
    private int contaMessaggi;
    private int contaDati;
    private Scene scene;

    public AbbonatoMemento(String nome, String cognome, String residenza, String numeroTelefono, String pianoTariffario, double conto, int contaChiamate, int contaMessaggi, int contaDati) {
        this.nome = nome;
        this.cognome = cognome;
        this.residenza = residenza;
        this.numeroTelefono = numeroTelefono;
        this.pianoTariffario = pianoTariffario;
        this.conto = conto;
        this.contaChiamate = contaChiamate;
        this.contaMessaggi = contaMessaggi;
        this.contaDati = contaDati;
    }


    // Metodi getter per gli attributi
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getResidenza() {
        return residenza;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getPianoTariffario() {
        return pianoTariffario;
    }

    public double getConto() {
        return conto;
    }

    public int getContaChiamate() {return contaChiamate; }

    public int getContaDati() { return contaDati; }

    // Metodo getter per la scena
    public Scene getScene() {
        return scene;
    }
}
