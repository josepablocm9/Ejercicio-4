public class Motocicleta extends Vehiculo {

    private int cilindraje;

    public Motocicleta(
            String placa,
            String marca,
            String modelo,
            double tarifaDiaria,
            int cilindraje) {

        super(placa, marca, modelo, tarifaDiaria);

        this.cilindraje = cilindraje;
    }

    @Override
    public double calcularCosto(int dias) {

        double costo = super.calcularCosto(dias);

        if (cilindraje > 250) {
            costo += 75;
        }

        return costo;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    @Override
    public String toString() {

        return super.toString()
                + " - Cilindraje: "
                + cilindraje + " cc";
    }
}