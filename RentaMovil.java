import java.util.ArrayList;

public class RentaMovil {

    private ArrayList<Vehiculo> vehiculos;
    private double ingresos;

    public RentaMovil() {
        vehiculos = new ArrayList<>();
        ingresos = 0;
    }

    public boolean registrarVehiculo(Vehiculo vehiculo) {

        if (vehiculo == null) {
            return false;
        }

        if (buscarVehiculo(vehiculo.getPlaca()) != null) {
            return false;
        }

        vehiculos.add(vehiculo);
        return true;
    }

    public Vehiculo buscarVehiculo(String placa) {

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.getPlaca().equalsIgnoreCase(placa)) {
                return vehiculo;
            }
        }

        return null;
    }
    public double cotizar(String placa, int dias) {

        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null || dias <= 0) {
            return -1;
        }

        return vehiculo.calcularCosto(dias);
    }

    public double alquilar(String placa, int dias) {

        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {
            return -1;
        }

        if (!vehiculo.isDisponible()) {
            return -2;
        }

        if (dias <= 0) {
            return -3;
        }

        double total = vehiculo.calcularCosto(dias);

        vehiculo.alquilar();

        ingresos += total;

        return total;
    }

    public boolean devolver(String placa) {

        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {
            return false;
        }

        if (vehiculo.isDisponible()) {
            return false;
        }

        vehiculo.devolver();

        return true;
    }
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    public double getIngresos() {
        return ingresos;
    }
    public int cantidadVehiculos() {
        return vehiculos.size();
    }
    public int cantidadDisponibles() {

        int cantidad = 0;

        for (Vehiculo vehiculo : vehiculos) {

            if (vehiculo.isDisponible()) {
                cantidad++;
            }
        }

        return cantidad;
    }
    public int cantidadAlquilados() {

        return cantidadVehiculos() - cantidadDisponibles();
    }

    public void mostrarFlota() {
        if (inventario.isEmpty()) {
            System.out.println("No hay vehículos registrados en la flota.");
            return;
        }
        System.out.println("\n--- LISTADO DE LA FLOTA ---");
        for (Vehiculo v : inventario) {
            v.mostrarDetalles();
        }
    }

    public void generarReporte() {
        int autosT = 0, autosD = 0;
        int motosT = 0, motosD = 0;
        int camionesT = 0, camionesD = 0;

        for (Vehiculo v : inventario) {
            if (v instanceof Automovil) {
                autosT++;
                if (v.isDisponible()) autosD++;
            } else if (v instanceof Motocicleta) {
                motosT++;
                if (v.isDisponible()) motosD++;
            } else if (v instanceof CamionetaCarga) {
                camionesT++;
                if (v.isDisponible()) camionesD++;
            }
        }

        System.out.println("\n================ REPORTE GENERAL ================");
        System.out.printf("Automóviles:  %d registrados | %d disponibles | %d alquilados\n", autosT, autosD, (autosT - autosD));
        System.out.printf("Motocicletas: %d registrados | %d disponibles | %d alquilados\n", motosT, motosD, (motosT - motosD));
        System.out.printf("Camionetas:   %d registrados | %d disponibles | %d alquilados\n", camionesT, camionesD, (camionesT - camionesD));
        System.out.printf("Ingresos Totales Acumulados: Q%.2f\n", ingresosTotales);
        System.out.println("==================================================");
    }

    public Vehiculo buscarPorPlaca(String placa) {
        if (placa == null) return null;
    for (Vehiculo v : inventario) {
        if (v.getPlaca().equalsIgnoreCase(placa.trim())) {
            return v;
        }
    }
    return null;
    }
}