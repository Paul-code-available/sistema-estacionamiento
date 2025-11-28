package interfaz;

import logica.vehiculos.Automovil;
import logica.vehiculos.Motocicleta;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        Estacionamiento estacionamiento = new Estacionamiento(5, 8);
        estacionamiento.inicializarEstacionamiento();

        Scanner in = new Scanner(System.in);

        try {
            System.out.println(estacionamiento.entradaVehiculo(new Motocicleta("1234567", "Yamaha", "443352", LocalDateTime.now()), 0,0));
            System.out.println(estacionamiento.entradaVehiculo(new Motocicleta("946615", "Yamaha", "443352", LocalDateTime.now()), 0,0));
            System.out.println(estacionamiento.entradaVehiculo(new Automovil("51545", "Toyota", "443352", LocalDateTime.now()), 1,0));
            System.out.println(estacionamiento.entradaVehiculo(new Automovil("51545", "Toyota", "443352", LocalDateTime.now()), 4,7));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

         

        /*
        try {
            System.out.println(estacionamiento.salidaVehiculo(0,0, usuario.pedirHoraSalida(in), usuario, in));
        } catch (Exception e) {
            System.err.println("Espacio no disponible " + e.getMessage());
        }
        */


        estacionamiento.verVehiculosEstacionados();

    }
}
