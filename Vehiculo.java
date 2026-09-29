public class Vehiculo {

    protected String placa;
    protected String marca;
    protected String modelo;
    protected double tarifaDiaria;
    protected boolean disponible;

    public Vehiculo(String placa, String marca, String modelo, double tarifaDiaria) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true;
    }

    public double calcularCosto(int dias) {
        return tarifaDiaria * dias;
    }

    public void alquilar() {
        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    @Override
    public String toString() {
        return placa + " - "
                + marca + " "
                + modelo
                + " - Q" + tarifaDiaria
                + " - "
                + (disponible ? "Disponible" : "Alquilado");
    }
}