public class CamionetaCarga extends Vehiculo {

    private double capacidadToneladas;

    public CamionetaCarga(
            String placa,
            String marca,
            String modelo,
            double tarifaDiaria,
            double capacidadToneladas) {

        super(placa, marca, modelo, tarifaDiaria);

        this.capacidadToneladas = capacidadToneladas;
    }

    @Override
    public double calcularCosto(int dias) {

        double costo = super.calcularCosto(dias);

        costo += 100 * capacidadToneladas * dias;

        return costo;
    }

    public double getCapacidadToneladas() {
        return capacidadToneladas;
    }

    @Override
    public String toString() {

        return super.toString()
                + " - Capacidad: "
                + capacidadToneladas
                + " toneladas";
    }
}