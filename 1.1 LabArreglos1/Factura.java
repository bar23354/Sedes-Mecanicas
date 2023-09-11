import java.util.*;

/**
 * Esta clase representa una factura en un taller mecánico. Contiene información sobre una reparación,
 * incluyendo el número de factura, el monto total, el tipo de servicio, la marca del carro y el modelo del carro.
 */
class Factura {
    private int numeroFactura;
    private float montoTotal;
    private String tipoServicio;
    private String marcaCarro;
    private int modeloCarro;

    public Factura(int numeroFactura, float montoTotal, String tipoServicio, String marcaCarro, int modeloCarro) {
        this.numeroFactura = numeroFactura;
        this.montoTotal = montoTotal;
        this.tipoServicio = tipoServicio;
        this.marcaCarro = marcaCarro;
        this.modeloCarro = modeloCarro;
    }

    // Getters for the fields of the invoice
    public int getNumeroFactura() {
        return numeroFactura;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public String getMarcaCarro() {
        return marcaCarro;
    }

    public int getModeloCarro() {
        return modeloCarro;
    }
    
    public static void main(String[] args) {
        List<Sede> sedes = new ArrayList<>();
        List<Factura> todasLasFacturas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Agregar Sede");
            System.out.println("2. Registrar Carro");
            System.out.println("3. Agregar Reparación");
            System.out.println("4. Calcular Estadísticas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarSede(sedes);
                    break;
                case 2:
                    registrarCarro(sedes);
                    break;
                case 3:
                    agregarReparacion(todasLasFacturas, sedes);
                    break;
                case 4:
                    calcularEstadisticas(todasLasFacturas);
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void agregarSede(List<Sede> sedes) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la sede: ");
        String nombreSede = scanner.nextLine();
        sedes.add(new Sede(nombreSede));
        System.out.println("Sede agregada correctamente.");
    }

    private static void registrarCarro(List<Sede> sedes) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la sede donde se registrará el carro: ");
        String nombreSede = scanner.nextLine();
        Sede sede = buscarSede(nombreSede, sedes);

        if (sede != null) {
            // Pedir detalles del carro al usuario y agregarlo a la sede
            System.out.print("Ingrese la placa del carro: ");
            String placa = scanner.nextLine();
            System.out.print("Ingrese la marca del carro: ");
            String marca = scanner.nextLine();
            System.out.print("Ingrese la línea del carro: ");
            String linea = scanner.nextLine();
            System.out.print("Ingrese el modelo del carro: ");
            int modelo = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Ingrese el nombre del propietario: ");
            String nombrePropietario = scanner.nextLine();

            Carro carro = new Carro(placa, marca, linea, modelo, nombrePropietario);
            sede.agregarCarro(carro);
            System.out.println("Carro registrado correctamente en la sede: " + sede.getNombreSede());
        } else {
            System.out.println("Sede no encontrada.");
        }
    }

    private static Sede buscarSede(String nombreSede, List<Sede> sedes) {
        for (Sede sede : sedes) {
            if (sede.getNombreSede().equalsIgnoreCase(nombreSede)) {
                return sede;
            }
        }
        return null;
    }

    private static void agregarReparacion(List<Factura> todasLasFacturas, List<Sede> sedes) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la placa del carro: ");
        String placaCarro = scanner.nextLine();
        System.out.print("Ingrese el número de factura: ");
        int numeroFactura = scanner.nextInt();
        System.out.print("Ingrese el monto total: ");
        float montoTotal = scanner.nextFloat();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el tipo de servicio: ");
        String tipoServicio = scanner.nextLine();
        
        Sede sede = buscarSedePorCarro(placaCarro, sedes);
        if (sede != null) {
            Factura factura = new Factura(numeroFactura, montoTotal, tipoServicio, sede.getNombreSede(), sede.getModeloCarro());
            todasLasFacturas.add(factura);
            System.out.println("Reparación agregada correctamente.");
        } else {
            System.out.println("Carro no encontrado en ninguna sede.");
        }
    }

    private static Sede buscarSedePorCarro(String placaCarro, List<Sede> sedes) {
        for (Sede sede : sedes) {
            for (Carro carro : sede.getCarrosAtendidos()) {
                if (carro.getPlaca().equalsIgnoreCase(placaCarro)) {
                    return sede;
                }
            }
        }
        return null;
    }

    private static void calcularEstadisticas(List<Factura> todasLasFacturas) {
        float sumaMontos = 0;
        int vehiculosReparadosEnUnaSemana = 0;
        Calendar unaSemanaAtras = Calendar.getInstance();
        unaSemanaAtras.add(Calendar.DAY_OF_MONTH, -7);
    
        Map<String, Integer> tiposDeServicios = new HashMap<>();
        Map<String, Integer> marcasDeCarros = new HashMap<>();
        Map<Integer, Integer> modelosDeCarros = new HashMap<>();
        float ingresosTotales = 0;
    
        for (Factura factura : todasLasFacturas) {
            sumaMontos += factura.getMontoTotal();
            int[] totalReparaciones = { 0 }; // Create an array to hold totalReparaciones

            String tipoServicio = factura.getTipoServicio();
            tiposDeServicios.put(tipoServicio, tiposDeServicios.getOrDefault(tipoServicio, 0) + 1);
            String marcaCarro = factura.getMarcaCarro();
            marcasDeCarros.put(marcaCarro, marcasDeCarros.getOrDefault(marcaCarro, 0) + 1);
            int modeloCarro = factura.getModeloCarro();
            modelosDeCarros.put(modeloCarro, modelosDeCarros.getOrDefault(modeloCarro, 0) + 1);
    
            ingresosTotales += factura.getMontoTotal();
            totalReparaciones[0]++;
    
            // Use totalReparaciones[0] within the lambda expression
            System.out.println("Tipos de servicios más solicitados:");
            tiposDeServicios.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(5)
                    .forEach(entry -> {
                        float percentage = ((float) entry.getValue() / totalReparaciones[0]) * 100;
                        System.out.println(entry.getKey() + ": " + percentage + "%");
                    });
        }
    
        float promedioMontos = (todasLasFacturas.size() > 0) ? sumaMontos / todasLasFacturas.size() : 0;
    
        System.out.println("Estadísticas:");
        System.out.println("Promedio de monto total de reparaciones: $" + promedioMontos);
        System.out.println("Porcentaje de vehículos reparados en una semana: " + ((float) vehiculosReparadosEnUnaSemana / todasLasFacturas.size()) * 100 + "%");
        System.out.println("Marcas de carros más comunes:");
        marcasDeCarros.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + ((float) entry.getValue() / todasLasFacturas.size()) * 100 + "%"));
        System.out.println("Modelos de carros más frecuentes:");
        modelosDeCarros.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + ((float) entry.getValue() / todasLasFacturas.size()) * 100 + "%"));
        System.out.println("Ingresos totales: $" + ingresosTotales);
    }    
}
