/*
 * Universidad del Valle de Guatemala
 * Programacion Orientada a Objetos
 * Roberto Barreda #23354
 */

import java.text.SimpleDateFormat;
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

    /**
     * Constructor de la clase Factura.
     *
     * @param numeroFactura   El número de factura.
     * @param montoTotal      El monto total de la reparación.
     * @param tipoServicio    El tipo de servicio realizado.
     * @param marcaCarro      La marca del carro.
     * @param modeloCarro     El modelo del carro.
     * @param fechaReparacion La fecha de la reparación.
     */
    public Factura(int numeroFactura, float montoTotal, String tipoServicio, String marcaCarro, int modeloCarro, Date fechaReparacion) {
        this.numeroFactura = numeroFactura;
        this.montoTotal = montoTotal;
        this.tipoServicio = tipoServicio;
        this.marcaCarro = marcaCarro;
        this.modeloCarro = modeloCarro;
    }

    /**
     * Obtiene el número de factura.
     *
     * @return El número de factura.
     */
    public int getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Obtiene el monto total de la reparación.
     *
     * @return El monto total de la reparación.
     */
    public float getMontoTotal() {
        return montoTotal;
    }

    /**
     * Obtiene el tipo de servicio realizado.
     *
     * @return El tipo de servicio.
     */
    public String getTipoServicio() {
        return tipoServicio;
    }

    /**
     * Obtiene la marca del carro.
     *
     * @return La marca del carro.
     */
    public String getMarcaCarro() {
        return marcaCarro;
    }

    /**
     * Obtiene el modelo del carro.
     *
     * @return El modelo del carro.
     */
    public int getModeloCarro() {
        return modeloCarro;
    }
    
    public static void main(String[] args) {
        List<Sede> sedes = new ArrayList<>();
        List<Factura> todasLasFacturas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú Principal:");
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
            System.out.print("Ingrese el número del modelo del carro: ");
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

    /*
     * Sistema agregarReparacion generado con ayuda de ChatGPT
     * Genera un método Java llamado agregarReparacion que toma una lista de Factura y una lista de Sede como parámetros. 
     * Este método solicita al usuario el nombre de una sede y busca la sede correspondiente en la lista proporcionada. 
     * Luego, solicita la placa de un carro y busca ese carro en la sede. 
     * Si el carro se encuentra, el usuario ingresa el número de factura, el monto total, el tipo de servicio y la fecha de reparación. 
     * Con esta información, se crea un objeto Factura y se agrega a la lista de facturas. 
     * Además, se genera un método llamado buscarCarroEnSede que busca un carro por placa en una sede y lo devuelve si se encuentra; de lo contrario, devuelve null. 
     * Se manejan posibles errores y se limpia el buffer del scanner cuando sea necesario.
     */
    private static void agregarReparacion(List<Factura> todasLasFacturas, List<Sede> sedes) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la sede donde se registrará la reparación: ");
        String nombreSede = scanner.nextLine();
        Sede sede = buscarSede(nombreSede, sedes);
    
        if (sede != null) {
            System.out.print("Ingrese la placa del carro: ");
            String placaCarro = scanner.nextLine();
            Carro carro = buscarCarroEnSede(placaCarro, sede);
    
            if (carro != null) {
                System.out.print("Ingrese el número de factura: ");
                int numeroFactura = scanner.nextInt();
                System.out.print("Ingrese el monto total: ");
                float montoTotal = scanner.nextFloat();
                scanner.nextLine(); // Limpiar el buffer
                System.out.print("Ingrese el tipo de servicio: ");
                String tipoServicio = scanner.nextLine();
                System.out.print("Ingrese la fecha de la reparación (yyyy-MM-dd): ");
                String fechaReparacionStr = scanner.nextLine();
    
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaReparacion = null;
                try {
                    fechaReparacion = dateFormat.parse(fechaReparacionStr);
                } catch (java.text.ParseException e) {
                    System.out.println("Formato de fecha incorrecto. Use yyyy-MM-dd.");
                    return;
                }
    
                Factura factura = new Factura(numeroFactura, montoTotal, tipoServicio, carro.getMarca(), carro.getModelo(), fechaReparacion);
                todasLasFacturas.add(factura);
                System.out.println("Reparación agregada correctamente.");
            } else {
                System.out.println("Carro no encontrado en la sede: " + sede.getNombreSede());
            }
        } else {
            System.out.println("Sede no encontrada.");
        }
    }    
    
    private static Carro buscarCarroEnSede(String placaCarro, Sede sede) {
        for (Carro carro : sede.getCarrosAtendidos()) {
            if (carro.getPlaca().equalsIgnoreCase(placaCarro)) {
                return carro;
            }
        }
        return null;
    }

    /*
     * Sistema generado con ayuda de ChatGPT:
     * "Genera un método Java llamado calcularEstadisticas que tome como parámetro una lista de objetos Factura llamada todasLasFacturas. 
     * Este método debe calcular las siguientes estadísticas:
     * 1. La suma de todos los montos totales de las facturas.
     * 2. El número de vehículos que fueron reparados en una semana a partir de la fecha actual.
     * 3. Un mapa que cuente la cantidad de veces que se ha realizado cada tipo de servicio.
     * 4. Un mapa que cuente la cantidad de veces que se ha reparado un carro de cada marca.
     * 5. Un mapa que cuente la cantidad de veces que se ha reparado un carro de cada modelo.
     * 6. El promedio del monto total de todas las reparaciones.
     * 7. Los 5 tipos de servicios más solicitados junto con el porcentaje de veces que se han realizado en relación con el total de reparaciones.
     * 8. Las 5 marcas de carros más comunes junto con el porcentaje de veces que se han reparado en relación con el total de reparaciones.
     * 8. Los 3 modelos de carros más frecuentes junto con el porcentaje de veces que se han reparado en relación con el total de reparaciones.
     * 10. El ingreso total generado por todas las facturas.
     * Asegúrate de que el código sea eficiente y que los resultados se impriman de manera clara en la consola. Todo en java."
     */
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
            int[] totalReparaciones = { 0 };

            String tipoServicio = factura.getTipoServicio();
            tiposDeServicios.put(tipoServicio, tiposDeServicios.getOrDefault(tipoServicio, 0) + 1);
            String marcaCarro = factura.getMarcaCarro();
            marcasDeCarros.put(marcaCarro, marcasDeCarros.getOrDefault(marcaCarro, 0) + 1);
            int modeloCarro = factura.getModeloCarro();
            modelosDeCarros.put(modeloCarro, modelosDeCarros.getOrDefault(modeloCarro, 0) + 1);
    
            ingresosTotales += factura.getMontoTotal();
            totalReparaciones[0]++;
    
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