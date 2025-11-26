package logica.vehiculos;

import java.time.LocalDateTime;

public abstract class Vehiculo {

    private String placa;
    private String marca;
    private String modelo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private double espacioRequerido;

    public Vehiculo(String placa, String marca, String modelo, LocalDateTime horaEntrada, double espacioRequerido) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = null;
        this.espacioRequerido = espacioRequerido;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getEspacioRequerido() {
        return espacioRequerido;
    }

    public void setEspacioRequerido(double espacioRequerido) {
        this.espacioRequerido = espacioRequerido;
    }

    public abstract double calcularTarifa();
}
