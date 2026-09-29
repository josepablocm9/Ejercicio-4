public class Automovil extends Vehiculo {

    private int pasajeros;
    private boolean automatica;

    public Automovil(
            String placa,
            String marca,
            String modelo,
            double tarifaDiaria,
            int pasajeros,
            boolean automatica) {

        super(placa, marca, modelo, tarifaDiaria);

        this.pasajeros = pasajeros;
        this.automatica = automatica;
    }

    @Override
    public double calcularCosto(int dias) {

        double costo = super.calcularCosto(dias);

        if (automatica) {
            costo += 50 * dias;
        }

        return costo;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public boolean isAutomatica() {
        return automatica;
    }

    @Override
    public String toString() {

        return super.toString()
                + " - Pasajeros: " + pasajeros
                + " - Transmisión: "
                + (automatica ? "Automática" : "Manual");
    }
}
