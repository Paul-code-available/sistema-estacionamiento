package interfaz;

import logica.vehiculos.Vehiculo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {

    public LocalDateTime pedirHoraSalida(Scanner in) {

        // pedir al usuario la hora
        System.out.print("Ingrese el hora de salida del Vehiculo (HH:mm): ");
        String hora = in.nextLine();

        try {
            // formato de la hora
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
            // convertimos la hora ingresada por el usuario(String) a LocalTime
            LocalTime horaSalida = LocalTime.parse(hora, formato);

            // ahora unimos la hora actual y la de salida para que sea valida
            return LocalDateTime.of(LocalDate.now(), horaSalida);

        } catch (Exception e) {
            System.out.println("Error en registro de salida: " + e.getMessage());
        }

        return null;
    }

    public int escogerMoto(Scanner in, int fila, int columna, ArrayList<Vehiculo>[][] vehiculosEstacionados) {
        System.out.println("Moto a salir:");
        System.out.println("1. " + vehiculosEstacionados[fila][columna].get(0).getPlaca());
        System.out.println("2. " + vehiculosEstacionados[fila][columna].get(1).getPlaca());
        System.out.println("Opcion: ");
        return Integer.parseInt(in.nextLine()) - 1;
    }


}
