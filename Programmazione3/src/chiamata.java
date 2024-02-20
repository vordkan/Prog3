public class chiamata implements command {
    private Abbonato abbonato;
    private int durata;
    private double costoChiamata;

    public chiamata(Abbonato abbonato) {
        this.abbonato = abbonato;
    }

    public int getDurata() {
        return this.durata;
    }


    public double execute() {
        this.durata = (int)(1.0 + Math.random() * 11.0);
        this.costoChiamata = (double)this.durata * 0.2;
        return this.costoChiamata;
    }
}
