package interfaz;

import logica.vehiculos.Automovil;
import logica.vehiculos.Motocicleta;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estacionamiento estacionamiento = new Estacionamiento(5, 8);
        estacionamiento.inicializarEstacionamiento();

        Scanner in = new Scanner(System.in);

        try {
            System.out.println(estacionamiento.estacionarVehiculo(new Motocicleta("1234567", "Yamaha", "443352", LocalDateTime.now()), 0,0));
            System.out.println(estacionamiento.estacionarVehiculo(new Motocicleta("1234567", "Yamaha", "443352", LocalDateTime.now()), 0,0));
            System.out.println(estacionamiento.estacionarVehiculo(new Automovil("1234567", "Yamaha", "443352", LocalDateTime.now()), 1,0));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }




        estacionamiento.verVehiculosEstacionados();


    }
}
