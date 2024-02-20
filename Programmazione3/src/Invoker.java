import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Invoker {
    private List<command> comandi = new ArrayList();
    private double conto;
    private int durata;
    private double costo;
    private int MB;
    private double costoMB;
/*-------------------------------------------------------------------------------------------------------------------*/
    // COMANDI
    public void aggiungiComando(command comando) {
        this.comandi.add(comando);
    }

/*--------------------------------------------------------------------------------------------------------------------*/

    // METODI GET
    public int getDurata() {
        return this.durata;
    }

    public double getCosto() {
        return this.costo;
    }

    public int getMB(){return this.MB;}
    public double getCostoMB() {
        return this.costoMB;
    }


    /*--------------------------------------------------------------------------------------------------------------------*/
    // AZIONI DEI COMANDI

    // EFFETTUA CHIAMATA
    public void effettuaChiamate(Abbonato abbonato) {
        double costoChiamate = 0.0;
        Iterator var4 = this.comandi.iterator();

        while(true) {
            while(true) {
                command comando;
                do {
                    // RICERCA DEL COMANDO
                    if (!var4.hasNext()) return;
                    comando = (command)var4.next();
                } while(!(comando instanceof chiamata));

                // SE L'ABBONATO NON HA UN PIANO TARIFFARIO E NON HA SOLDI SUL CONTO ALLORA MESSAGGIO DI ERRORE
                if (abbonato.getPianoTariffario().equals("null") && abbonato.getConto() <= 0.0) {}
                else if (!abbonato.getPianoTariffario().equals("null")) {
                    String codicePromozione = abbonato.getPianoTariffario();
                    Promozione promozione = cerca.cercaPromo(codicePromozione);
                    String offerta = promozione.getOfferta();

                    if(offerta.equals("minuti illimitati")){
                        costoChiamate += comando.execute();
                        // STA OTTENENDO I MINUTI IN CHIAMATA
                        int minuti = ((chiamata)comando).getDurata();
                        this.durata = minuti;
                        this.costo = 0;
                    }
                    else{
                        costoChiamate += comando.execute();
                        // STA OTTENENDO I MINUTI IN CHIAMATA
                        int minuti = ((chiamata)comando).getDurata();
                        this.durata = minuti;
                        this.costo = costoChiamate;
                        double nuovoConto = abbonato.getConto() - costoChiamate;
                        this.conto = nuovoConto;
                        abbonato.setConto(this.conto);
                    }
                }
                else {
                    costoChiamate += comando.execute();

                    // STA OTTENENDO I MINUTI IN CHIAMATA
                    int minuti = ((chiamata)comando).getDurata();
                    this.durata = minuti;
                    this.costo = costoChiamate;
                    double nuovoConto = abbonato.getConto() - costoChiamate;
                    this.conto = nuovoConto;

                    abbonato.setConto(this.conto);
                }
            }
        }
    }

    // UTILIZZO DEI DATI
    public void usoDati(Abbonato abbonato) {
        double costoDati = 0.0;
        Iterator var4 = this.comandi.iterator();

        while(true) {
            while(true) {
                command comando;
                do {
                    if (!var4.hasNext()) return;
                    comando = (command)var4.next();
                } while(!(comando instanceof connessioneDati));

                if (abbonato.getConto() <= 0.0 && abbonato.getPianoTariffario().equals("null")) { }
                else if (!abbonato.getPianoTariffario().equals("null")) {
                    String codicePromozione = abbonato.getPianoTariffario();
                    Promozione promozione = cerca.cercaPromo(codicePromozione);
                    String offerta = promozione.getOfferta();
                    if(offerta.equals("dati illimitati")){
                        costoDati += comando.execute();
                        int MB = ((connessioneDati)comando).getMb();
                        this.MB = MB;
                        this.costo = 0;
                    }
                    else{
                        costoDati += comando.execute();
                        int MB = ((connessioneDati)comando).getMb();
                        this.MB = MB;
                        this.costoMB = costoDati;
                        double nuovoConto = abbonato.getConto() - costoDati;
                        this.conto = nuovoConto;
                        abbonato.setConto(this.conto);
                    }
                }
                else {
                    costoDati += comando.execute();
                    int MB = ((connessioneDati)comando).getMb();
                    this.MB = MB;
                    this.costoMB = costoDati;
                    double nuovoConto = abbonato.getConto() - costoDati;
                    this.conto = nuovoConto;

                    abbonato.setConto(this.conto);
                }
            }
        }
    }
}
