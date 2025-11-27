package logica.vehiculos;

import java.time.LocalDateTime;

/**
 * Esta clase representa un automóvil dentro del sistema de estacionamiento.
 * Un automóvil ocupa un espacio predeterminado de 1 y
 * tiene una tarifa fija por hora.
 */
public class Automovil extends Vehiculo {

    public Automovil(String placa, String marca, String modelo, LocalDateTime horaEntrada, double espacioRequerido) {
        super(placa, marca, modelo, horaEntrada, 1);
    }

    /**
     * Tarifa por hora de los automoviles y que se retorna a la clase
     * abstracta vehiculo.
     * Sirve para calcular el costo total.
     *
     * @return tarifa por hora para un automóvil
     */
    @Override
    public int tarifaPorHora() {
        return 15;
    }
}
