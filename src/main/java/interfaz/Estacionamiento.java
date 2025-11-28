package interfaz;

import logica.vehiculos.Vehiculo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Estacionamiento {

    private int filas;
    private int columnas;

    private double[][] espacioEstacionamiento;
    private ArrayList<Vehiculo>[][] vehiculosEstacionados;

    public Estacionamiento(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        espacioEstacionamiento = new double[filas][columnas];
        vehiculosEstacionados = new ArrayList[filas][columnas];
    }

    public void inicializarEstacionamiento() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                espacioEstacionamiento[i][j] = 0;
                vehiculosEstacionados[i][j] = new ArrayList<>();
            }
        }
    }

    public String entradaVehiculo(Vehiculo vehiculo, int fila, int columna) {
        /*
            si el espacio requerido por el vehiculo es 0.5 y el espacio disponible del estacionamiento es
            menor o igual a 0.5 sumanos y asignamos el espacio requerido por el vehiculo al espacio total
            del estacionamiento.
            el espacio maximo por lugar de estacionamiento es 1, es decir; 1 carro o 2 motos.
            si el espacio total del estacionamiento es 0.5 asignamos una 'M' de moto a la vista
            del estacionamiento que significa que aún hay espacio para otra moto y si es igual a
            1 una 'X' que significa que el espacio ya esta lleno.
         */

        if (espacioEstacionamiento[fila][columna] == 1) {
            throw new IllegalStateException("El espacio ya esta ocupado.");
        }

        if (espacioEstacionamiento[fila][columna] <= 0.5 && vehiculo.getEspacioRequerido() <= 0.5 || vehiculo.getEspacioRequerido() == 1) {
            espacioEstacionamiento[fila][columna] += vehiculo.getEspacioRequerido();

            if (espacioEstacionamiento[fila][columna] == 0.5) {
                vehiculosEstacionados[fila][columna].add(vehiculo);
                return "Moto estacionada, hay espacio para una más";
            }
            if (espacioEstacionamiento[fila][columna] == 1) {
                vehiculosEstacionados[fila][columna].add(vehiculo);
                return "Vehiculo estacionado, espacio lleno";
            }
        }
        return "No se pudo estacionar el vehiculo.";
    }

    public String salidaVehiculo(int fila, int columna, LocalDateTime horaSalida, Usuario usuario, Scanner in) {
        // ver que pedo con las motos
        int indice = 0;
        // si en el lugar hay 2 motos el usuario tendrá que escoger una basado en la placa
        if (vehiculosEstacionados[fila][columna].get(1) != null) {
            indice = usuario.escogerMoto(in,fila, columna, vehiculosEstacionados);
        }

        Vehiculo v = vehiculosEstacionados[fila][columna].get(indice); // accedemos al vehiculo
        v.setHoraSalida(horaSalida); // asignamos la hora de salida
        vehiculosEstacionados[fila][columna].remove(indice); // borramos el vehiculo

        generarTicket(v);

        return "El vehiculo ha salido con exito";
    }

    public void generarTicket(Vehiculo v) {
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        Duration d = Duration.between(v.getHoraEntrada(), v.getHoraSalida());

        long minutos = d.toMinutes();
        long horas = minutos / 60;
        long minutosRestantes = minutos % 60;

        System.out.println("===================Ticket===================");
        System.out.println("Tipo: " + v.toString());
        System.out.println("Placa: " + v.getPlaca());
        System.out.println("Hora de entrada: " + v.getHoraEntrada().format(formatoHora));
        System.out.println("Hora de salida: " +  v.getHoraSalida().format(formatoHora));
        System.out.println("Duración total: " + horas + "h " + minutosRestantes + "m");
        System.out.println("Total a pagar: " + v.calcularTarifa());
    }

    public void verVehiculosEstacionados() {
        imprimirTitulo();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                System.out.print(i + "," +  j);
                if (vehiculosEstacionados[i][j].isEmpty()) {
                    System.out.print("[Vacio]\t\t");
                } else {
                    for (Vehiculo v : vehiculosEstacionados[i][j]) {
                        System.out.print("[" + v.toString() + "]");
                    }
                    System.out.print("\t\t");
                }
            }
            System.out.println();
        }
    }

    public void imprimirTitulo() {
        System.out.println("======================================================Estacionamiento====================================================");
    }

}
