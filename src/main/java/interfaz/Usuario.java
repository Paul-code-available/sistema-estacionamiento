package interfaz;

import logica.vehiculos.Automovil;
import logica.vehiculos.Camioneta;
import logica.vehiculos.Motocicleta;
import logica.vehiculos.Vehiculo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {

    private ArrayList<String> placas = new ArrayList<>();
    /**
     *
     * @param in -> para interactuar con el usuario
     * @return retorna la hora de salida del vehiculo
     */
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
            System.out.println("Error en hora de salida: " + e.getMessage());
        }

        return null;
    }

    /**
     *
     * @param vehiculosEstacionados le pasamos la matriz donde almacenamos los vehiculos
     * en caso de que el mismo lugar tenga 2 motos el usuario deberá elegir por su placa
     * cual de ellas sacara
     * @return retorna el indice de la moto escogida
     */
    public int escogerMoto(Scanner in, int fila, int columna, ArrayList<Vehiculo>[][] vehiculosEstacionados) {
        System.out.println("Moto a salir:");
        System.out.println("1. " + vehiculosEstacionados[fila][columna].get(0).getPlaca());
        System.out.println("2. " + vehiculosEstacionados[fila][columna].get(1).getPlaca());
        System.out.println("Opcion: ");
        return Integer.parseInt(in.nextLine()) - 1;
    }

    /**
     * en general sirve para pedir los datos del vehiculo al usuario
     * @return -> retornara determinado vehiculo dependiendo cual escogio
     * el usuario
     */
    public Vehiculo registrarVehiculo(Scanner in) {
        System.out.println("\t========Registrar Vehiculo===========");
        try {
            int tipoVehiculo = 0;
            do {

                System.out.println("Tipo de Vehiculo (1. Moto, 2. Automovil, 3. Camioneta): ");
                tipoVehiculo = Integer.parseInt(in.nextLine());

            } while (tipoVehiculo < 1 || tipoVehiculo > 3);
            String placa;
            boolean existe = false;
            do {
                existe = false;
                System.out.print("Ingrese la placa del Vehiculo: ");
                placa = in.nextLine();

                if (existePlaca(placa)) {
                    System.out.println("La placa ya existe");
                    existe = true;
                    continue;
                }

                placas.add(placa);
            } while (existe); // verificar

            System.out.print("Ingrese el marca del Vehiculo: ");
            String marca = in.nextLine();
            System.out.print("Ingrese el modelo del Vehiculo: ");
            String modelo = in.nextLine();

            if (tipoVehiculo == 1) {
                return new Motocicleta(placa, marca, modelo, LocalDateTime.now());
            } else if (tipoVehiculo == 2) {
                return new Automovil(placa, marca, modelo, LocalDateTime.now());
            } else {
                return new Camioneta(placa, marca, modelo, LocalDateTime.now());
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Valor incorrecto");
        }
    }

    public boolean existePlaca(String placa) {
        for (String p : placas) {
            if (p.equalsIgnoreCase(placa)) {
                return true;
            }
        }
        return false;
    }

    /**
     *  es el menu con el interactuamos con el usuario
     * @return -> retorna la opcion escogida por el usuario
     */
    public int menu(Scanner in) {
        int opcion = 0;
        do {
            try {
                System.out.println("==========Menu=========");
                System.out.println("1. Registrar entrada");
                System.out.println("2. Registrar salida");
                System.out.println("3. Ver estacionamiento");
                System.out.println("4. Generar reporte");
                System.out.println("5. Salir");
                System.out.println("Opcion: ");
                opcion = Integer.parseInt(in.nextLine());

            } catch (IllegalArgumentException e) {
                System.out.println("Valor incorrecto " + e.getMessage());
            };

        } while (opcion < 1 || opcion > 5);

        return opcion;
    }

    /**
     *
     * @return -> metodo para pedir la fila al usuario
     */
    public int pedirFila(Scanner in) {
        int fila = -1;
        do {

            try {
                System.out.print("Ingrese la Fila: ");
                fila = Integer.parseInt(in.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Valor incorrecto " + e.getMessage());
            }

        } while (fila < 0 || fila > 5);

        return fila;
    }

    /**
     *
     * @return -> metodo para pedir la columna al usuario
     */
    public int pedirColumna(Scanner in) {
        int columna = -1;
        do {

            try {
                System.out.print("Ingrese la Columna: ");
                columna = Integer.parseInt(in.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Valor incorrecto " + e.getMessage());
            }

        } while (columna < 0 || columna > 5);

        return columna;
    }
}
