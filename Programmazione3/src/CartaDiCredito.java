
public class CartaDiCredito implements PaymentStrategy {
    private String numeroCarta;
    private double saldoConto;
    private double ricarica;

    public CartaDiCredito(String numeroCarta, double saldoConto) {
        this.numeroCarta = numeroCarta;
        this.saldoConto = saldoConto;
    }

    public double getRicarica(){return this.ricarica;}
    public double getSaldoConto(){ return this.saldoConto;}
    public boolean pay(double pagato) {
        boolean effettuare = true;
        this.saldoConto -= pagato;
        this.ricarica = pagato;
        return effettuare;
    }
}
