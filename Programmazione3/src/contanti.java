import java.util.Date;

public class contanti implements PaymentStrategy {
    private double ricarica;
    public contanti() {
    }

    public double getRicarica(){return this.ricarica;}

    public boolean pay(double pagato) {
        boolean effettuare = true;
        this.ricarica = pagato;
        return effettuare;
    }
}
