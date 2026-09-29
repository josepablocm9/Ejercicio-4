import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static RentaMovil renta = new RentaMovil();

    public static void main(String[] args) {

        cargarVehiculosIniciales();

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarVehiculo();
                   break;
                case 2:
                    renta.mostrarFlota();
                    break;
                case 3:
                    cotizarAlquiler();
                    break;
                case 4:
                    realizarAlquiler();
                    break;
                case 5:
                    realizarDevolucion();
                    break;
                case 6:
                    renta.generarReporte();
                    break;
                case 0:
                    System.out.println("\nPrograma finalizado.");
                    break;
                default:
                    System.out.println("\nOpción inválida.");
            }
        } while (opcion != 0);
        scanner.close();
    }


    private static void mostrarMenu() {

        System.out.println("\n================================");
        System.out.println("          RENTAMOVIL");
        System.out.println("================================");
        System.out.println("1. Registrar vehículo");
        System.out.println("2. Consultar flota");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. Confirmar alquiler");
        System.out.println("5. Registrar devolución");
        System.out.println("6. Mostrar reporte general");
        System.out.println("0. Salir");
        System.out.println("================================");
    }


    private static void registrarVehiculo() {

        System.out.println("\n===== REGISTRAR VEHÍCULO =====");

        System.out.println("1. Automóvil");
        System.out.println("2. Motocicleta");
        System.out.println("3. Camioneta de carga");

        int tipo = leerEntero("Seleccione el tipo: ");

        String placa = leerTexto("Placa: ");
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        double tarifa = leerDoublePositivo("Tarifa diaria: ");

        Vehiculo vehiculo = null;

        switch (tipo) {

            case 1:

                int pasajeros = leerEnteroPositivo("Cantidad de pasajeros: ");

                System.out.println("¿La transmisión es automática?");
                System.out.println("1. Sí");
                System.out.println("2. No");

                int opcionTransmision = leerEntero("Seleccione: ");

                boolean automatica = opcionTransmision == 1;

                vehiculo = new Automovil(
                        placa,
                        marca,
                        modelo,
                        tarifa,
                        pasajeros,
                        automatica
                );

                break;

            case 2:

                int cilindraje = leerEnteroPositivo("Cilindraje (cc): ");

                vehiculo = new Motocicleta(
                        placa,
                        marca,
                        modelo,
                        tarifa,
                        cilindraje
                );
                break;
            case 3:
                double capacidad = leerDoublePositivo(
                        "Capacidad máxima (toneladas): "
                );
                vehiculo = new CamionetaCarga(
                        placa,
                        marca,
                        modelo,
                        tarifa,
                        capacidad
                );
                break;
            default:
                System.out.println("Tipo de vehículo inválido.");
                return;
        }
        if (renta.registrarVehiculo(vehiculo)) {
            System.out.println("Vehículo registrado correctamente.");
        } else {
            System.out.println(
                    "No se pudo registrar. "
                    + "La placa puede estar repetida."
            );
        }
    }


    private static void cotizarAlquiler() {
        System.out.println("\n===== COTIZAR ALQUILER =====");
        String placa = leerTexto("Ingrese la placa: ");
        int dias = leerEnteroPositivo(
                "Cantidad de días: "
        );

        Vehiculo vehiculo = renta.buscarPorPlaca(placa);
        if (vehiculo == null) {
            System.out.println("No existe un vehículo con esa placa.");
            return;
        }

        double total = renta.cotizar(placa, dias);

        System.out.println("\n----- COTIZACIÓN -----");
        System.out.println("Placa: " + vehiculo.getPlaca());
        System.out.println("Marca: " + vehiculo.getMarca());
        System.out.println("Modelo: " + vehiculo.getModelo());

        if (vehiculo.isDisponible()) {
            System.out.println("Disponibilidad: Disponible");
        } else {
            System.out.println("Disponibilidad: Alquilado");
        }
        System.out.printf("Total: Q%.2f%n", total);
        System.out.println(
                "\nLa cotización no modifica los ingresos "
                + "ni la disponibilidad."
        );
    }


    private static void realizarAlquiler() {
        System.out.println("\n===== CONFIRMAR ALQUILER =====");
        String placa = leerTexto("Ingrese la placa: ");
        Vehiculo vehiculo = renta.buscarPorPlaca(placa);

        if (vehiculo == null) {
            System.out.println("No existe un vehículo con esa placa.");
            return;
        }

        if (!vehiculo.isDisponible()) {
            System.out.println(
                    "El vehículo ya está alquilado."
            );
            return;
        }

        int dias = leerEnteroPositivo(
                "Cantidad de días: "
        );

        double total = renta.cotizar(placa, dias);
        System.out.printf(
                "Total a pagar: Q%.2f%n",
                total
        );

        System.out.println("¿Desea confirmar el alquiler?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int confirmacion = leerEntero("Seleccione: ");
        if (confirmacion == 1) {
            double alquiler = renta.alquilar(placa, dias);
            if (alquiler > 0) {
                System.out.println(
                        "Alquiler confirmado correctamente."
                );
            } else {
                System.out.println(
                        "No se pudo realizar el alquiler."
                );
            }
        } else {
            System.out.println(
                    "Alquiler cancelado. "
                    + "No se modificó la información."
            );
        }
    }

    private static void realizarDevolucion() {
        System.out.println("\n===== DEVOLUCIÓN =====");
        String placa = leerTexto(
                "Ingrese la placa del vehículo: "
        );

        Vehiculo vehiculo = renta.buscarPorPlaca(placa);
        if (vehiculo == null) {
            System.out.println(
                    "No existe un vehículo con esa placa."
            );
            return;
        }

        if (vehiculo.isDisponible()) {
            System.out.println(
                    "El vehículo ya está disponible."
            );
            return;
        }

        if (renta.devolver(placa)) {
            System.out.println(
                    "Devolución registrada correctamente."
            );
        } else {
            System.out.println(
                    "No se pudo registrar la devolución."
            );
        }
    }

    private static void cargarVehiculosIniciales() {

        // AUTOMÓVILES

        renta.registrarVehiculo(
                new Automovil(
                        "A001",
                        "Toyota",
                        "Corolla",
                        300.00,
                        5,
                        true
                )
        );

        renta.registrarVehiculo(
                new Automovil(
                        "A002",
                        "Honda",
                        "Civic",
                        250.00,
                        5,
                        false
                )
        );

        // MOTOCICLETAS

        renta.registrarVehiculo(
                new Motocicleta(
                        "M001",
                        "Honda",
                        "CBR",
                        150.00,
                        300
                )
        );

        renta.registrarVehiculo(
                new Motocicleta(
                        "M002",
                        "Yamaha",
                        "FZ",
                        120.00,
                        200
                )
        );

        // CAMIONETAS

        renta.registrarVehiculo(
                new CamionetaCarga(
                        "C001",
                        "Toyota",
                        "Hilux",
                        200.00,
                        1.5
                )
        );

        renta.registrarVehiculo(
                new CamionetaCarga(
                        "C002",
                        "Ford",
                        "Ranger",
                        250.00,
                        2.0
                )
        );
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println(
                    "El valor no puede estar vacío."
            );
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(
                        scanner.nextLine().trim()
                );
            } catch (NumberFormatException e) {
                System.out.println(
                        "Debe ingresar un número entero."
                );
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor > 0) {
                return valor;
            }
            System.out.println(
                    "El valor debe ser mayor que cero."
            );
        }
    }

    private static double leerDoublePositivo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(
                        scanner.nextLine().trim()
                );
                if (valor > 0) {
                    return valor;
                }
                System.out.println(
                        "El valor debe ser mayor que cero."
                );
            } catch (NumberFormatException e) {
                System.out.println(
                        "Debe ingresar un número válido."
                );
            }
        }
    }
}