package logica.vehiculos;

import java.time.LocalDateTime;

/**
 * Esta clase representa una motocicleta dentro del sistema de estacionamiento.
 * Una motocicleta ocupa un espacio predeterminado de 0.5 y
 * tiene una tarifa fija por hora.
 */
public class Motocicleta extends Vehiculo{

    public Motocicleta(String placa, String marca, String modelo, LocalDateTime horaEntrada) {
        super(placa, marca, modelo, horaEntrada, 0.5);
    }

    /**
     * Tarifa por hora de las motocicletas y que se retorna a la clase
     * abstracta vehiculo.
     * Sirve para calcular el costo total.
     *
     * @return tarifa por hora para una motoclicleta
     */
    public int tarifaPorHora() {
        return 10;
    }

    public String toString() {
        return "Moto";
    }
}
