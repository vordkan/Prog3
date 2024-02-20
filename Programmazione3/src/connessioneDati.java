public class connessioneDati implements command {
    private Abbonato abbonato;
    private int Mb;
    private double prezzo;

    public connessioneDati(Abbonato abbonato) {
        this.abbonato = abbonato;
    }

    public int getMb() {
        return this.Mb;
    }

    public double getCostoDati() {
        return this.prezzo;
    }

    public double execute() {
        this.Mb = (int)(1.0 + Math.random() * 10000.0);
        this.prezzo = (double)this.Mb * 5.0E-4;
        return this.prezzo;
    }
}
