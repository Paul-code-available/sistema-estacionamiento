package interfaz;

import logica.vehiculos.Automovil;
import logica.vehiculos.Vehiculo;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Estacionamiento {

    private int filas;
    private int columnas;

    private double[][] espacioEstacionamiento;
    private ArrayList<Vehiculo>[][] vehiculosEstacionados;
    private ArrayList<Vehiculo> historialVehiculos = new ArrayList<>();
    private double ingresosTotales;


    public Estacionamiento(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        espacioEstacionamiento = new double[filas][columnas];
        vehiculosEstacionados = new ArrayList[filas][columnas];

    }

    /**
     * inicializa las matrices
     * la matriz espacioEstacionamiento lleva el calculo de los espacios por estacionamiento,
     * mientras que la de vehiculosEstacionados es donde almacenamos los Vehiculos(objetos)
     * esta misma matriz detro de cada celda contiene un arrayList que puede almacenar un
     * maximo de 2 motos o 1 automovil o camioneta
     */
    public void inicializarEstacionamiento() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                espacioEstacionamiento[i][j] = 0;
                vehiculosEstacionados[i][j] = new ArrayList<>();
            }
        }
    }

    /**
     *
     * @param vehiculo -> vehiculo que le pedimos al usuario
     * @param fila -> fila donde se almacenará el vehiculo
     * @param columna -> columna donde se almacenará el vehiculo
     * @return retorna un mensaje dependiendo la operacion que se realizo
     * o si no se pudo realizar ninguna
     */
    public String entradaVehiculo(Vehiculo vehiculo, int fila, int columna) {

        double espacioActual = espacioEstacionamiento[fila][columna];
        double requerido = vehiculo.getEspacioRequerido();
        // verificamos si el espacio esta ocupado
        if (espacioActual == 1) {
            return "El espacio ya está completamente ocupado";
        }
        // si es un automovil o camioneta
        if (requerido == 1) {
            if (espacioActual == 0) {
                espacioEstacionamiento[fila][columna] = 1;
                vehiculosEstacionados[fila][columna].add(vehiculo);
                return "Automóvil estacionado con exito!!";
            } else {
                return "No hay espacio suficiente para un automóvil en este lugar.";
            }
        }
        // si es moto
        if (requerido == 0.5) {
            // cuando es la primera moto
            if (espacioActual == 0) {
                espacioEstacionamiento[fila][columna] = 0.5;
                vehiculosEstacionados[fila][columna].add(vehiculo);
                return "Moto estacionada con exito!!, hay espacio para una mas en la misma posicion";
            }
            // cuando es la segunda moto
            if (espacioActual == 0.5) {
                espacioEstacionamiento[fila][columna] = 1;
                vehiculosEstacionados[fila][columna].add(vehiculo);
                return "Moto estacionada con exito, espacio lleno.";
            }
            return "No se puede estacionar otra moto aquí.";
        }

        return "No existe ese tipo de vehiculo.";
    }
    /**
     *
     * @param horaSalida -> se la pedimos al usuario cuando el vehiculo saldrá
     * @param usuario -> pasamos la instancia del objeto usuario para poder utilizar sus metodos
     * @param in -> lo pasamos como parametro porque usuario.escogerMoto lo utiliza
     */
    public void salidaVehiculo(int fila, int columna, LocalDateTime horaSalida, Usuario usuario, Scanner in) {
        if (vehiculosEstacionados[fila][columna].isEmpty()) {
            throw new IndexOutOfBoundsException("El estacionamiento esta vacio");
        }
        int indice = 0;
        if (vehiculosEstacionados[fila][columna].size() == 2) {
            indice = usuario.escogerMoto(in,fila, columna, vehiculosEstacionados);
        }

        Vehiculo v = vehiculosEstacionados[fila][columna].get(indice); // accedemos al vehiculo
        v.setHoraSalida(horaSalida); // asignamos la hora de salida
        vehiculosEstacionados[fila][columna].remove(indice); // borramos el vehiculo
        espacioEstacionamiento[fila][columna] -= v.getEspacioRequerido(); // reseteamos el espacio

        historialVehiculos.add(v);
        ingresosTotales += v.calcularTarifa();

        generarTicket(v);

    }
    /**
     * @param v -> es de tipo vehiculo, lo pasamos al momento de la salida del vehiculo,
     * este es un metodo que se manda a llamar en salidaVehiculo.
     * simplemente sirve para generar el ticket
     */
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
    // muestra la vista del estacionamiento
    public void verVehiculosEstacionados() {
        imprimirTitulo();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                StringBuilder celda = new StringBuilder(); // contruyo una string que en este caso es la celda
                System.out.print(i + "," +  j);
                celda.append("[ ");

                for (Vehiculo v : vehiculosEstacionados[i][j]) {
                    if (v.getEspacioRequerido() == 0.5) {
                        celda.append("M");
                    } else {
                        celda.append("C");
                    }
                }
                celda.append(" ]");
                System.out.print(celda); // la imprimo
                if (vehiculosEstacionados[i][j].size() == 2) {
                    System.out.print("\t");
                } else {
                    System.out.print("\t\t");
                }
            }
            System.out.println();
        }
    }

    public void imprimirTitulo() {
        System.out.println("========================Estacionamiento==========================");
    }

    /**
     * Genera un reporte algo general de lo que paso en el uso del estacionamiento,
     * mostrando el historial de vehiculos que han entrado y salido, los ingresos
     * que se generaron en el transcurso del uso del programa, que tan ocupado esta
     * el estacionamiento y como se ve.
     * Los vehivulos que entraron necesitan haber salido para que se muestren,
     * si no se indica que aun no sale ninguno.
     */
    public void generarReporte(){

        System.out.println("\n====================REPORTE GENERAL====================");

        System.out.println("\nHistorial de vehiculos que ingresaron y salieron: ");

        if (historialVehiculos.isEmpty()){
            System.out.println("Aun no ha salido ningun vehiculo");
        }
        else{
            for (int i = 0; i < historialVehiculos.size(); i++){

                Vehiculo v = historialVehiculos.get(i);
                System.out.println("Modelo del vehiculo: " + v.getModelo());
                System.out.println("Hora de entrada: " + v.getHoraEntrada().toLocalTime());

                if (v.getHoraSalida() != null) {
                    System.out.println("Hora de salida: " + v.getHoraSalida().toLocalTime());
                }
                else{
                    System.out.println("Hora de salida: el vehiculo entro, pero aun no ha salido");
                }

                System.out.println();
            }
        }

        System.out.println("\nIngresos totales: " + ingresosTotales + " pesos");

        double espaciosTotales = filas * columnas;
        double espaciosOcupados = 0;



        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                if ( !vehiculosEstacionados[i][j].isEmpty() ){
                    espaciosOcupados += espacioEstacionamiento[i][j];
                }
            }
        }

        double porcentajeOcupacion = (espaciosOcupados/espaciosTotales) * 100;
        System.out.println("El porcentaje de ocupacion es del: " + porcentajeOcupacion + "%");

        System.out.println("Vista actual de estacionamiento: \n");
        verVehiculosEstacionados();

    }
}
