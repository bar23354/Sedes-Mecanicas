/*
 * Universidad del Valle de Guatemala
 * Programacion Orientada a Objetos
 * Roberto Barreda #23354
 */

/**
 * Esta clase representa un carro en una sede de un taller mecánico.
 */
class Carro {
    private String placa;
    private String marca;
    private String linea;
    private int modelo;
    private String nombrePropietario;

    public Carro(String placa, String marca, String linea, int modelo, String nombrePropietario) {
        this.placa = placa;
        this.marca = marca;
        this.linea = linea;
        this.modelo = modelo;
        this.nombrePropietario = nombrePropietario;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getLinea() {
        return linea;
    }

    public int getModelo() {
        return modelo;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }
}