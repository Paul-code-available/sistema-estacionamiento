package logica.vehiculos;

import java.time.LocalDateTime;

public class Motocicleta extends Vehiculo{

    public Motocicleta(String placa, String marca, String modelo, LocalDateTime horaEntrada, double espacioRequerido) {
        super(placa, marca, modelo, horaEntrada, espacioRequerido = 0.5);
    }
}
