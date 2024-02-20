import java.util.Date;

public class CartaDiDebito implements PaymentStrategy {
    private String numeroCarta;
    private double saldoConto;
    private double ricarica;

    public CartaDiDebito(String numeroCarta, double saldoConto) {
        this.numeroCarta = numeroCarta;
        this.saldoConto = saldoConto;
    }
    public double getRicarica(){return this.ricarica;}


    public boolean pay(double pagato) {
        boolean effettuare = true;
        if (this.saldoConto < pagato) {
            return effettuare;
        } else {
            this.saldoConto -= pagato;
            this.ricarica = pagato;
            return effettuare;
        }
    }
}
