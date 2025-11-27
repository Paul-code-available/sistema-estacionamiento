package logica.vehiculos;

import java.time.LocalDateTime;

/*
 * Representa un vehículo dentro del sistema de estacionamiento.
 * Cada vehículo cuenta con la siguiente informacion: placa, marca, modelo,
 * registra la hora de entrada y salida al estacionamiento y el espacio requerido.
 * La clase tambien calcula la tarifa total tomando en cuenta el tiempo transcurrido,
 * cobrando una hora completa si el tiempo es menor a una hora y redondeando
 * cualquier fracción hacia arriba.
 */

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


    /**
     * Porporciona la tarifa por hora de cada tipo de vehículo.
     * Cada clase hija debe proporcionar su propia tarifa, ya que cambia
     * dependiendo del tipo de vehiculo.
     */
    public abstract int tarifaPorHora();

    /**
     * Calcula el total a pagar basandose en el tiempo transcurrido.
     * Se cobra una hora completa si el tiempo es menor a una hora.
     * Toda fracción de hora se redondea hacia arriba para cobrarla completa.
     *
     * @return el total a pagar
     */
    public double calcularTarifa(){

        int horas = getHoraSalida().getHour() - getHoraEntrada().getHour();
        int minutos = getHoraSalida().getMinute() - getHoraEntrada().getMinute();

        if (minutos < 0) {
            horas -= 1;
            minutos += 60;
        }

        double horaDecimal = horas + (minutos / 60.0);
        int tarifaPorHora = tarifaPorHora();

        if (horaDecimal < 1.0){
            return tarifaPorHora;
        }
        else{
            double horasCobradas = Math.ceil(horaDecimal);
            return horasCobradas * tarifaPorHora;
        }
    }
}
