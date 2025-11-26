package logica.vehiculos;

import java.time.LocalDateTime;

public class Automovil extends Vehiculo {

    private double tarifaPorHora = 15;

    public Automovil(String placa, String marca, String modelo, LocalDateTime horaEntrada, double espacioRequerido, double tarifaPorHora) {
        super(placa, marca, modelo, horaEntrada, espacioRequerido);
        this.tarifaPorHora = tarifaPorHora;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    @Override
    public double calcularTarifa() {

    }
}
