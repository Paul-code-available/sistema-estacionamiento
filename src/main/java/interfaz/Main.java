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

        int opcion = 0;
        do {

            opcion = usuario.menu(in);

            switch (opcion) {
                case 1:
                    try {
                        System.out.println(estacionamiento.entradaVehiculo(usuario.registrarVehiculo(in), usuario.pedirFila(in), usuario.pedirColumna(in)));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    System.out.println("Presione ENTER para continuar:");
                    in.nextLine();
                    break;
                case 2:
                    try {
                        estacionamiento.salidaVehiculo(usuario.pedirFila(in), usuario.pedirColumna(in), usuario.pedirHoraSalida(in), usuario, in);
                    } catch (Exception e) {
                        System.err.println("Error: " + e.getMessage());
                    }

                    System.out.println("Presione ENTER para continuar:");
                    in.nextLine();
                    break;
                case 3:
                    estacionamiento.verVehiculosEstacionados();

                    System.out.println("Presione ENTER para continuar:");
                    in.nextLine();
                    break;
                case 4:
                    estacionamiento.generarReporte();

                    System.out.println("Presione ENTER para continuar:");
                    in.nextLine();
                    break;
            }

        } while (opcion != 5);

    }
}
