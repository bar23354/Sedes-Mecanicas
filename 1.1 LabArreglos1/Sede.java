import java.util.*;

/**
 * Esta clase representa una sede de un taller mecánico.
 */
class Sede {
    private String nombreSede;
    private List<Carro> carrosAtendidos;

    public Sede(String nombreSede) {
        this.nombreSede = nombreSede;
        this.carrosAtendidos = new ArrayList<>();
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public List<Carro> getCarrosAtendidos() {
        return carrosAtendidos;
    }

    public void agregarCarro(Carro carro) {
        carrosAtendidos.add(carro);
    }

    public int getModeloCarro() {
        return 0;
    }
}