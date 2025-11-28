package logica.vehiculos;

import java.time.LocalDateTime;

/**
 * Esta clase representa una camioneta dentro del sistema de estacionamiento.
 * Una camioneta ocupa un espacio predeterminado de 1 y
 * tiene una tarifa fija por hora.
 */
public class Camioneta extends Vehiculo{

    public Camioneta(String placa, String marca, String modelo, LocalDateTime horaEntrada, double espacioRequerido) {
        super(placa, marca, modelo, horaEntrada, 1);

    }

    /**
     * Tarifa por hora de las camionetas y que se retorna a la clase
     * abstracta vehiculo.
     * Sirve para calcular el costo total.
     *
     * @return tarifa por hora para una camioneta
     */
    public int tarifaPorHora() {
        return 20;
    }

    public String toString() {
        return "Camioneta";
    }
}
